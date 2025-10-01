package org.example.controller;

import org.example.entity.Employee;
import org.example.entity.WorkLog;
import org.example.repository.WorkLogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/work_log")
public class WorkLogController {
    private final WorkLogRepository workLogRepository;

    public WorkLogController(WorkLogRepository workLogRepository) {
        this.workLogRepository = workLogRepository;
    }

    @GetMapping("/{employee_id}")
    public List<WorkLog> getWorkLogByEmployee(@PathVariable("employee_id") Long employeeId) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        return workLogRepository.findEmployeeWorkLogByEmployee(employee);
    }
}
