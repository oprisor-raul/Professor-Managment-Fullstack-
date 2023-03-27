package com.oprisorraul.demo.model.modelRequests;

import com.oprisorraul.demo.controller.ProfessorController;
import com.oprisorraul.demo.model.Professor;
import com.oprisorraul.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

public class NewCourseRequest {
    private String name;
    private DayOfWeek dayOfWeek;
    private LocalTime localTime;


    private ProfessorRepository professorRepository;


    public NewCourseRequest(String name, DayOfWeek dayOfWeek, LocalTime localTime, Integer professorId) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.localTime = localTime;
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

    public Professor getProfessorId() {
        return new Professor(1,"Raul","oprisorraul@gmail.com");
    }
}
