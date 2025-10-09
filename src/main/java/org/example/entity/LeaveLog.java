package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "leave_log")
public class LeaveLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_log_id")
    private Long leaveLogId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "leave_date")
    private LocalDate leaveDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type")
    private LeaveType leaveType;
    @Column(name = "leave_hours")
    private Double leaveHours;

    public LeaveLog(Long leaveLogId, Employee employee, LocalDate leaveDate, LeaveType leaveType, Double leaveHours) {
        this.leaveLogId = leaveLogId;
        this.employee = employee;
        this.leaveDate = leaveDate;
        this.leaveType = leaveType;
        this.leaveHours = leaveHours;
    }

    public LeaveLog() {
    }

    public Long getLeaveLogId() {
        return leaveLogId;
    }

    public void setLeaveLogId(Long leaveLogId) {
        this.leaveLogId = leaveLogId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Double getLeaveHours() {
        return leaveHours;
    }

    public void setLeaveHours(Double leaveHours) {
        this.leaveHours = leaveHours;
    }
}
