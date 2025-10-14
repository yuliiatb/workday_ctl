package org.example.controller;

import org.example.dto.EmployeeReportDTO;
import org.example.entity.*;
import org.example.repository.EmployeeRepository;
import org.example.repository.LeaveLogRepository;
import org.example.repository.ScheduleRepository;
import org.example.repository.WorkLogRepository;
import org.example.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/work_log")
public class WorkLogController {
    private final WorkLogRepository workLogRepository;
    private final LeaveLogRepository leaveLogRepository;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;

    public WorkLogController(WorkLogRepository workLogRepository, LeaveLogRepository leaveLogRepository, ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository) {
        this.workLogRepository = workLogRepository;
        this.leaveLogRepository = leaveLogRepository;
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/{employee_id}")
    public List<EmployeeReportDTO> getWorkLogByEmployee(@PathVariable("employee_id") Long employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);

        List<WorkLog> workLogs = workLogRepository.findEmployeeWorkLogByEmployee(employee);
        List<LeaveLog> leaveLogs = leaveLogRepository.findByEmployee(employee);

        // Obtener el empleado y su horario
        Optional<Employee> currentEmployee = employeeRepository.findByEmployeeId(employeeId);
        double scheduleHours;
        if (currentEmployee.isPresent() && currentEmployee.get().getScheduleId() != null) {
            scheduleHours = currentEmployee.get().getScheduleId().getTotalScheduleHours();
        } else {
            scheduleHours = 0.0;
        }

        // Calcular horas totales trabajadas
        List<EmployeeReportDTO> reportList = workLogs.stream().map(log -> {
            double totalHoursWorked = (Duration.between(log.getLogStartTime(), log.getLogEndTime()).toMinutes()
                    - Duration.between(log.getBreakStart(), log.getBreakEnd()).toMinutes()) / 60.0;
            totalHoursWorked = Math.round(totalHoursWorked * 100.0) / 100.0;

            EmployeeReportDTO dto = new EmployeeReportDTO();

            // Calcular horas horas extra
            double extraHours = Math.max(0, totalHoursWorked - scheduleHours);
            extraHours = Math.round(extraHours * 100.0) / 100.0;

            // Comprobar si las horas de ausencia fueron también en los días que los trabajadores estaban presentes
            List<LeaveLog> leavesOnDate = leaveLogs.stream()
                    .filter(l -> l.getLeaveDate().equals(log.getWorkDate()))
                    .collect(Collectors.toList());

            // Calcular las horas faltantes según las fechas de los fichajes
            double recoverHours = 0.0;
            boolean hasNonRecoverableLeave = false;

            for (LeaveLog leave : leavesOnDate) {
                if (leave.getLeaveType() == LeaveType.PERMISO_SE_RECUPERAN) {
                    recoverHours += leave.getLeaveHours();
                } else {
                    hasNonRecoverableLeave = true;
                }
            }

            // Calcular las horas faltantes
            double hoursLeft;
            if (hasNonRecoverableLeave) {
                hoursLeft = 0.0;
            } else {
                // Si el tipo de baja es PERMISO_SE_RECUPERAN
                hoursLeft = Math.max(0, (scheduleHours - totalHoursWorked) + recoverHours);
            }
            hoursLeft = Math.round(hoursLeft * 100.0) / 100.0;

            dto.setDate(log.getWorkDate());
            dto.setLogStartTime(log.getLogStartTime());
            dto.setLogEndTime(log.getLogEndTime());
            dto.setBreakStart(log.getBreakStart());
            dto.setBreakEnd(log.getBreakEnd());
            dto.setTotalHours(totalHoursWorked);
            dto.setExtraHours(extraHours);
            dto.setHoursLeft(hoursLeft);

            // Mostrar el tipo de ausencia, si es necesario
            if (!leavesOnDate.isEmpty()) {
                dto.setLeaveType(leavesOnDate.get(0).getLeaveType());
                dto.setAbsenceHours(leavesOnDate.get(0).getLeaveHours());
            } else {
                dto.setLeaveType(null);
                dto.setAbsenceHours(0.0);
            }
            return dto;

        }).collect(Collectors.toList());

        // Horas de ausencia
        for (LeaveLog leave : leaveLogs) {
            EmployeeReportDTO dto = new EmployeeReportDTO();
            dto.setDate(leave.getLeaveDate());
            dto.setAbsenceHours(leave.getLeaveHours());
            dto.setLeaveType(leave.getLeaveType());

            // Incluir las horas de ausencia a las horas faltantes si son del tipo PERMISO_SE_RECUPERAN
            dto.setHoursLeft(
                    leave.getLeaveType() == LeaveType.PERMISO_SE_RECUPERAN ? leave.getLeaveHours() : 0.0
            );
            reportList.add(dto);
        }
        return reportList;
    }
}
