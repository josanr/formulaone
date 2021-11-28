package ru.josanr.formulaone.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class RacerLapInfo {

    private final String id;
    private final String name;
    private final String team;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public RacerLapInfo(String id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Duration getLapTime() {
        if (startTime == null || endTime == null) {
            return null;
        }

        return Duration.between(startTime, endTime);
    }

    @Override
    public String toString() {
        var startTimeStr = startTime != null ? startTime.toString() : "";
        var endTimeStr = endTime != null ? endTime.toString() : "";
        return "RacerLapInfo{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", team='" + team + '\'' +
            ", startTime=" + startTimeStr +
            ", endTime=" + endTimeStr +
            '}';
    }
}
