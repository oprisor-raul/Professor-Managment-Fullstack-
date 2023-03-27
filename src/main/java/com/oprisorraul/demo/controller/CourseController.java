package com.oprisorraul.demo.controller;

import com.oprisorraul.demo.model.Course;
import com.oprisorraul.demo.model.Professor;
import com.oprisorraul.demo.model.modelRequests.NewCourseRequest;
import com.oprisorraul.demo.repository.CourseRepository;
import com.oprisorraul.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private ProfessorRepository professorRepository;

    public CourseController(CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/{courseId}/professors/{professorId}")
    public Course addProfessorToCourse(@PathVariable Integer courseId, @PathVariable Integer professorId) {
        // get course and professor by ID
        Course course = courseRepository.getById(courseId);
        Professor professor = professorRepository.getById(professorId);

        // add professor to course and course to professor
        course.setProfessor(professor);

        // update course and professor
        courseRepository.updateProfessorIdByCourse(courseId, professorId);

        // return updated course
        return course;
    }

    @PostMapping
    public Course addCourse(@RequestBody NewCourseRequest request) {
        Course myCourse = new Course();
        myCourse.setProfessor(request.getProfessorId());
        myCourse.setName(request.getName());
        myCourse.setDayOfWeek(request.getDayOfWeek());
        myCourse.setLocalTime(request.getLocalTime());
        return courseRepository.save(myCourse);
    }

}
