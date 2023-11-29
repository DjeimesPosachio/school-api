package com.example.demo.Dto;

import com.example.demo.model.cadastro.Aluno;
import com.example.demo.model.cadastro.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaDTO {
    private Long id;
    private String nome;

    public DisciplinaDTO converter(Disciplina disciplina) {
        return DisciplinaDTO.builder()
                .id(disciplina.getId())
                .nome(disciplina.getNome()).build();
    }
}
