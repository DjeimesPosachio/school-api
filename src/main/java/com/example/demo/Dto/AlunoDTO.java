package com.example.demo.Dto;

import com.example.demo.model.cadastro.Aluno;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO {

    private Long id;
    private String nome;

    public AlunoDTO converter(Aluno aluno) {
        return AlunoDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome()).build();
    }
}
