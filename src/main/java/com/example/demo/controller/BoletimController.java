package com.example.demo.controller;

import com.example.demo.Dto.BoletimDTO;
import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import com.example.demo.service.BoletimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boletim")
public class BoletimController {
    private final BoletimService boletimService;

    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @GetMapping("/{alunoId}")
    public ResponseEntity<List<BoletimDTO>> obterBoletimAluno(@PathVariable Long alunoId) {
        List<BoletimDTO> boletim = boletimService.gerarBoletim(alunoId);
        if (!boletim.isEmpty()) {
            return ResponseEntity.ok(boletim);
        }
        return ResponseEntity.notFound().build();
    }
}

