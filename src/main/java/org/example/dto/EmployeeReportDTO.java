package org.example.dto;

import org.example.entity.LeaveType;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeeReportDTO {
    private LocalDate date;
    private LocalTime logStartTime;
    private LocalTime logEndTime;
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private double totalHours;
    private double absenceHours;
    private double extraHours;
    private double hoursLeft;
    private LeaveType leaveType;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public double getAbsenceHours() {
        return absenceHours;
    }

    public void setAbsenceHours(double absenceHours) {
        this.absenceHours = absenceHours;
    }

    public double getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(double extraHours) {
        this.extraHours = extraHours;
    }

    public double getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(double hoursLeft) {
        this.hoursLeft = hoursLeft;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }
}
