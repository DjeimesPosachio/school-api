package com.example.demo.controller;

import com.example.demo.model.cadastro.Disciplina;
import com.example.demo.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaService.getAllDisciplinas();
    }

    @PostMapping
    public Disciplina createDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaService.createDisciplina(disciplina);
    }

    @GetMapping("/{disciplinaId}")
    public ResponseEntity<Disciplina> buscarDisciplinaPorId(@PathVariable Long disciplinaId) {
        Disciplina disciplina = disciplinaService.getDisciplinaPorId(disciplinaId);
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{disciplinaId}")
    public ResponseEntity<Disciplina> atualizarDisciplina(
            @PathVariable Long disciplinaId,
            @RequestBody Disciplina disciplinaAtualizada
    ) {
        Disciplina disciplina = disciplinaService.updateDisciplina(disciplinaId, disciplinaAtualizada);
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{disciplinaId}")
    public ResponseEntity<Void> excluirDisciplina(@PathVariable Long disciplinaId) {
        boolean delete = disciplinaService.deleteDisciplina(disciplinaId);
        if (delete) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
