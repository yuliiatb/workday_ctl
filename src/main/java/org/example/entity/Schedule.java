package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;
    @Column(name = "start_time")
    private LocalTime scheduleStartTime;
    @Column(name = "end_time")
    private LocalTime scheduleEndTime;
    @Column(name = "break_duration")
    private Double breakDuration;
    @Column(name = "total_hours")
    private Double totalScheduleHours;

    public Schedule(Long scheduleId, LocalTime scheduleStartTime, LocalTime scheduleEndTime, Double breakDuration, Double totalHours) {
        this.scheduleId = scheduleId;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndTime = scheduleEndTime;
        this.breakDuration = breakDuration;
        this.totalScheduleHours = totalHours;
    }

    public Schedule() {
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalTime getStartTime() {
        return scheduleStartTime;
    }

    public void setStartTime(LocalTime scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public LocalTime getEndTime() {
        return scheduleEndTime;
    }

    public void setEndTime(LocalTime scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public Double getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(Double breakDuration) {
        this.breakDuration = breakDuration;
    }

    public Double getTotalScheduleHours() {
        return totalScheduleHours;
    }

    public void setTotalScheduleHours(Double totalScheduleHours) {
        this.totalScheduleHours = totalScheduleHours;
    }
}
