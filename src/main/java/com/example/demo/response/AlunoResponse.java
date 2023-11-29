package com.example.demo.response;

import com.example.demo.model.cadastro.Curso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoResponse {

    private String nome;
    private List<Curso> cursos;
}
