package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "employee_first_name")
    private String employeeFirstName;
    @Column(name = "employee_last_name")
    private String employeeLastName;
    @Column(name = "employee_user_name", unique = true)
    private String employeeUserName;
    @Column(name = "employee_password")
    private String employeePassword;
    @Column(name = "employee_role")
    private String employeeRole;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule scheduleId;

    public Employee(Long employeeId, String employeeFirstName, String employeeLastName, String employeeUserName, String employeePassword, String employeeRole, Schedule scheduleId) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeUserName = employeeUserName;
        this.employeePassword = employeePassword;
        this.employeeRole = employeeRole;
        this.scheduleId = scheduleId;
    }

    public Employee() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Schedule getSchedule() {
        return scheduleId;
    }

    public void setSchedule(Schedule scheduleId) {
        this.scheduleId = scheduleId;
    }
}
