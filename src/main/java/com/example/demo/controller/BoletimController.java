package com.example.demo.controller;

import com.example.demo.Dto.BoletimDTO;
import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/boletim")
public class BoletimController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{alunoId}")
    public BoletimDTO getBoletim(@PathVariable Long alunoId) {
        Optional<Aluno> aluno = alunoService.getAlunoById(alunoId);
        // Lógica para calcular o status e criar o DTO do boletim
        // ...
        System.out.println(aluno.get());
        return new BoletimDTO();
    }
}
