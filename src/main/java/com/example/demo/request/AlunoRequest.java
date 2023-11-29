package com.example.demo.request;

import lombok.Data;

import java.util.List;

@Data
public class AlunoRequest {

    private String nome;
    private List<Long> cursosId;
}
