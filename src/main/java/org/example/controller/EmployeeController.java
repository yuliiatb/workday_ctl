package org.example.controller;

import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    // Crear un empleado
    @PostMapping
    public Employee addEmployee (@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Obtener todos los empleados
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employee_id}")
    public Employee getEmployeeById(@PathVariable("employee_id") Long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId).orElse(null);
    }
}