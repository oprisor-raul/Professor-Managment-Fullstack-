package com.oprisorraul.demo.model.modelRequests;

import com.oprisorraul.demo.model.Professor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class NewCourseRequest {
    private String name;
    private DayOfWeek dayOfWeek;
    private LocalTime localTime;
    private Integer professorId;

    public NewCourseRequest(String name, DayOfWeek dayOfWeek, LocalTime localTime, Integer professorId) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.localTime = localTime;
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }
}
