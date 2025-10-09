package org.example.controller;

import org.example.dto.EmployeeReportDTO;
import org.example.entity.Employee;
import org.example.entity.LeaveLog;
import org.example.entity.Schedule;
import org.example.entity.WorkLog;
import org.example.repository.EmployeeRepository;
import org.example.repository.LeaveLogRepository;
import org.example.repository.ScheduleRepository;
import org.example.repository.WorkLogRepository;
import org.example.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
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

            // Calcular horas horas extra
            double extraHours = Math.max(0, totalHoursWorked - scheduleHours);
            extraHours = Math.round(extraHours * 100.0) / 100.0;

            // Calcular las horas faltantes
            double hoursLeft = Math.max(0, scheduleHours - totalHoursWorked);
            hoursLeft = Math.round(hoursLeft * 100.0) / 100.0;

            EmployeeReportDTO dto = new EmployeeReportDTO();
            dto.setDate(log.getWorkDate());
            dto.setLogStartTime(log.getLogStartTime());
            dto.setLogEndTime(log.getLogEndTime());
            dto.setBreakStart(log.getBreakStart());
            dto.setBreakEnd(log.getBreakEnd());
            dto.setTotalHours(totalHoursWorked);
            dto.setExtraHours(extraHours);
            dto.setHoursLeft(hoursLeft);
            return dto;
        }).collect(Collectors.toList());

        // Horas de ausencia
        for (LeaveLog leaveLog: leaveLogs) {
            for (LeaveLog leave : leaveLogs) {
                EmployeeReportDTO dto = new EmployeeReportDTO();
                dto.setDate(leave.getLeaveDate());
                dto.setLogStartTime(null);
                dto.setLogEndTime(null);
                dto.setBreakStart(null);
                dto.setBreakEnd(null);
                dto.setAbsenceHours(leave.getLeaveHours());
                dto.setLeaveType(leave.getLeaveType());
                reportList.add(dto);
            }
        }

        return reportList;
    }
}
