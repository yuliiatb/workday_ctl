package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "work_log")
public class WorkLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worklog_id")
    private Long workLogId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "work_date")
    private LocalDate workDate;
    @Column(name = "log_start_time")
    private LocalTime logStartTime;
    @Column(name = "log_end_time")
    private LocalTime logEndTime;
    @Column(name = "break_start")
    private LocalTime breakStart;
    @Column(name = "break_end")
    private LocalTime breakEnd;

    public WorkLog(Long workLogId, Employee employee, LocalDate workDate, LocalTime logStartTime, LocalTime logEndTime, LocalTime breakStart, LocalTime breakEnd) {
        this.workLogId = workLogId;
        this.employee = employee;
        this.workDate = workDate;
        this.logStartTime = logStartTime;
        this.logEndTime = logEndTime;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
    }

    public WorkLog() {
    }

    public Long getLogId() {
        return workLogId;
    }

    public void setLogId(Long workLogId) {
        this.workLogId = workLogId;
    }

    public Employee getEmployeeId() {
        return employee;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employee = employeeId;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getLogStartTime() {
        return logStartTime;
    }

    public void setLogStartTime(LocalTime logStartTime) {
        this.logStartTime = logStartTime;
    }

    public LocalTime getLogEndTime() {
        return logEndTime;
    }

    public void setLogEndTime(LocalTime logEndTime) {
        this.logEndTime = logEndTime;
    }

    public LocalTime getBreakStart() {
        return breakStart;
    }

    public void setBreakStart(LocalTime breakStart) {
        this.breakStart = breakStart;
    }

    public LocalTime getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(LocalTime breakEnd) {
        this.breakEnd = breakEnd;
    }

}
