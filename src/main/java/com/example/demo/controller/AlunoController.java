package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> getAllAlunos() {
        return alunoService.getAllAlunos();
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoService.createAluno(aluno);
    }

    @PutMapping("/{alunoId}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long alunoId, @RequestBody Aluno alunoAtualizado) {
        Aluno aluno = alunoService.updateAluno(alunoId, alunoAtualizado);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{alunoId}")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long alunoId) {
        boolean excluiu = alunoService.deleteAluno(alunoId);
        if (excluiu) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}