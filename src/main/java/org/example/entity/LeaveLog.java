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
    @Column(name = "leave_type")
    private LeaveType leaveType;

    public LeaveLog(Long leaveLogId, Employee employee, LocalDate leaveDate, LeaveType leaveType) {
        this.leaveLogId = leaveLogId;
        this.employee = employee;
        this.leaveDate = leaveDate;
        this.leaveType = leaveType;
    }

    public LeaveLog() {
    }

    public Long getLeaveLogId() {
        return leaveLogId;
    }

    public void setLeaveLogId(Long leaveLogId) {
        this.leaveLogId = leaveLogId;
    }

    public Employee getEmployeeId() {
        return employee;
    }

    public void setEmployeeId(Employee employee) {
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
}
