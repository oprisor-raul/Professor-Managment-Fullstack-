package com.oprisorraul.demo;

import com.oprisorraul.demo.model.Course;
import com.oprisorraul.demo.repository.CourseRepository;
import com.oprisorraul.demo.repository.ProfessorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.oprisorraul.demo.repository")
@ComponentScan("com.oprisorraul.demo.controller")
public class DemoApplication {
	private final ProfessorRepository professorRepository;
	private final CourseRepository courseRepository;

	public DemoApplication(ProfessorRepository professorRepository, CourseRepository courseRepository) {
		this.professorRepository = professorRepository;
		this.courseRepository = courseRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
