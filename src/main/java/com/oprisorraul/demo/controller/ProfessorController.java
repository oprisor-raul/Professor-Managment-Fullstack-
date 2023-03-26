package com.oprisorraul.demo.controller;



import com.oprisorraul.demo.model.Professor;
import com.oprisorraul.demo.model.modelRequests.NewProfessorRequest;
import com.oprisorraul.demo.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/professor")
public class ProfessorController {
    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public List<Professor> getProfessor() {
        return professorRepository.findAll();
    }

    @PostMapping
    public void addProfessor(@RequestBody NewProfessorRequest request) {
        Professor professor = new Professor();
        professor.setName(request.name());
        professor.setEmail(request.email());
        professorRepository.save(professor);
    }

    @DeleteMapping("{professorId}")
    public void deleteProfessor(@PathVariable("professorId") Integer id) {
        professorRepository.deleteById(id);
    }

    @PutMapping("{professorId}")
    public void updateProfessor(@PathVariable("professorId") Integer id, @RequestBody NewProfessorRequest request) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
        professor.setName(request.name());
        professor.setEmail(request.email());
        professorRepository.save(professor);
    }
}
