package com.example.demo.Dto;

import com.example.demo.model.cadastro.Aluno;
import com.example.demo.model.cadastro.Curso;
import com.example.demo.model.cadastro.Disciplina;
import com.example.demo.model.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoletimDTO {
    private AlunoDTO aluno;
    private CursoDTO curso;
    private DisciplinaDTO disciplina;
    private Double nota;
    private Status status;
}

