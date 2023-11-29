package com.example.demo.Dto;

import com.example.demo.model.Aluno;
import com.example.demo.model.Curso;
import com.example.demo.model.Disciplina;
import lombok.Data;

import java.util.List;

@Data
public class BoletimDTO {
    private Aluno aluno;
    private Curso curso;
    private Disciplina disciplina;
    private double nota;
    private String status;

    public BoletimDTO(Aluno aluno, Curso curso, Disciplina disciplina, double notaAluno, String status) {
    }

    public BoletimDTO() {

    }
}

