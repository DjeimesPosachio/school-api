package com.example.demo.service;

import com.example.demo.Dto.BoletimDTO;
import com.example.demo.model.Aluno;
import com.example.demo.model.Disciplina;
import com.example.demo.model.Nota;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BoletimService {
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public BoletimService(AlunoRepository alunoRepository, CursoRepository cursoRepository, DisciplinaRepository disciplinaRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<BoletimDTO> gerarBoletim(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));

        List<Disciplina> disciplinasDoCurso = aluno.getCurso().getDisciplinas();
        List<BoletimDTO> boletim = new ArrayList<>();

        for (Disciplina disciplina : disciplinasDoCurso) {
            double notaAluno = obterNotaAlunoNaDisciplina(aluno, disciplina);
            String status = notaAluno >= 7.0 ? "Aprovado" : "Reprovado";

            BoletimDTO boletimDTO = new BoletimDTO(aluno, aluno.getCurso(), disciplina, notaAluno, status);
            boletim.add(boletimDTO);
        }

        return boletim;
    }

    private double obterNotaAlunoNaDisciplina(Aluno aluno, Disciplina disciplina) {
        Optional<Nota> notaDoAlunoNaDisciplina = disciplina.getNotas().stream()
                .filter(nota -> nota.getAluno().equals(aluno))
                .findFirst();

        return notaDoAlunoNaDisciplina.map(Nota::getValor).orElse(0.0);
    }
}


