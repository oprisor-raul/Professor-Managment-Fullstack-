package com.oprisorraul.demo.controller;

import com.oprisorraul.demo.model.Professor;
import com.oprisorraul.demo.repository.ProfessorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/professor")
public class ProfessorController {
    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Professor> getProfessor() {
        return professorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Professor> addProfessor(@RequestBody Professor professor) {
        professorRepository.save(professor);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @DeleteMapping("{professorId}")
    public void deleteProfessor(@PathVariable("professorId") Integer id) {
        professorRepository.deleteById(id);
    }

    @PutMapping("{professorId}")
    public void updateProfessor(@PathVariable("professorId") Integer id, @RequestBody Professor professor) {
        Professor existingProfessor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
        existingProfessor.setName(professor.getName());
        existingProfessor.setEmail(professor.getEmail());
        professorRepository.save(existingProfessor);
    }
}
