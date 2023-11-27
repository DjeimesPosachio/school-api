package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Nota> notas;

    public double calcularMedia() {
        if (notas.isEmpty()) {
            return 0.0;
        }

        double soma = 0;
        int count = 0;
        for (Nota nota : notas) {
            if (nota.getValor() >= 0 && nota.getValor() <= 10) {
                soma += nota.getValor();
                count++;
            }
        }
        return count > 0 ? soma / count : 0.0;
    }
}
