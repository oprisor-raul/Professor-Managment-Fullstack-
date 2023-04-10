package com.oprisorraul.demo.controller;

import com.oprisorraul.demo.model.Course;
import com.oprisorraul.demo.model.Professor;
import com.oprisorraul.demo.repository.CourseRepository;
import com.oprisorraul.demo.repository.ProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/professor")
public class ProfessorController {
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    public ProfessorController(ProfessorRepository professorRepository, CourseRepository courseRepository) {
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Professor> getProfessor() {
        return professorRepository.findAll();
    }

    @GetMapping("{professorMail}")
    public ResponseEntity<Integer> getAllCoursesByProfessorId(@PathVariable(value = "professorMail") String professorMail) {
        Optional<Object> optionalFoundProfessor = professorRepository.findByEmail(professorMail);
        if (optionalFoundProfessor.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Professor foundProfessor = (Professor) optionalFoundProfessor.get();
            return new ResponseEntity<>(foundProfessor.getId(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Professor> addProfessor(@RequestBody Professor professor) {
        if (professorRepository.findByEmail(professor.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        professorRepository.save(professor);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @DeleteMapping("{professorId}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("professorId") Integer professorId) {
        if (!professorRepository.existsById(professorId)) {
//            throw new ResourceNotFoundException("Not found Professor with id = " + professorId);
            return ResponseEntity.notFound().build();
        }
        List<Course> courses = courseRepository.findByProfessorId(professorId);
        for (Course c : courses) {
            courseRepository.deleteById(c.getId());
        }
        professorRepository.deleteById(professorId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{professorId}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("professorId") Integer professorId, @RequestBody Professor professor) {
        if (!professorRepository.existsById(professorId)) {
//            throw new ResourceNotFoundException("Not found Professor with id = " + professorId);
            return ResponseEntity.notFound().build();
        }
        Professor existingProfessor = professorRepository.findById(professorId).orElseThrow(() -> new RuntimeException("Professor not found"));
        existingProfessor.setName(professor.getName());
        existingProfessor.setEmail(professor.getEmail());
        professorRepository.save(existingProfessor);
        return new ResponseEntity<>(existingProfessor, HttpStatus.CREATED);
    }
}
