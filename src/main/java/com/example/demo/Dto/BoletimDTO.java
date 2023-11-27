package com.example.demo.Dto;

import lombok.Data;

import java.util.List;

@Data
public class BoletimDTO {
    private String nomeAluno;
    private String nomeCurso;
    private List<DisciplinaDTO> disciplinas;
    private String status;

}

