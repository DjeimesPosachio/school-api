package com.example.demo.Dto;

import com.example.demo.model.cadastro.Aluno;
import com.example.demo.model.cadastro.Curso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    private Long id;
    private String nome;

    public CursoDTO converter(Curso curso) {
        return CursoDTO.builder()
                .id(curso.getId())
                .nome(curso.getNome()).build();
    }
}
