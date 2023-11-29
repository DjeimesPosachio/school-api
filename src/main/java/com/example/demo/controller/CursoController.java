package com.example.demo.controller;

import com.example.demo.model.cadastro.Curso;
import com.example.demo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.getAllCursos();
    }

    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        return cursoService.createCurso(curso);
    }

    @PutMapping
    public Curso updateCurso(@RequestBody Curso curso) {
        return cursoService.updateCurso(curso);
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long cursoId) {
        Curso curso = cursoService.getCursoPorId(cursoId);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cursoId}")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long cursoId) {
        boolean excluiu = cursoService.deleteCurso(cursoId);
        if (excluiu) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}