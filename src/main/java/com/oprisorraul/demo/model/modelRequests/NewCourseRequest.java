package com.oprisorraul.demo.model.modelRequests;

import com.oprisorraul.demo.model.Professor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class NewCourseRequest {
    private String name;
    private DayOfWeek dayOfWeek;

    private LocalTime localTime;
}
