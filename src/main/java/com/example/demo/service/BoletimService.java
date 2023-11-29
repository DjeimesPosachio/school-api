package com.example.demo.service;

import com.example.demo.Dto.AlunoDTO;
import com.example.demo.Dto.BoletimDTO;
import com.example.demo.Dto.CursoDTO;
import com.example.demo.Dto.DisciplinaDTO;
import com.example.demo.model.cadastro.Aluno;
import com.example.demo.model.cadastro.Curso;
import com.example.demo.model.cadastro.Disciplina;
import com.example.demo.model.enumeration.Status;
import com.example.demo.model.negocio.Nota;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.DisciplinaRepository;
import com.example.demo.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoletimService {

    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<BoletimDTO> gerarBoletim(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado"));

        List<Disciplina> disciplinas = new ArrayList<>();

        aluno.getCursos().forEach(alunoCurso -> {
            disciplinas.addAll(alunoCurso.getCurso().getDisciplinas());
        });

        List<Nota> notasAluno = notaRepository.findByAluno(aluno);

        List<BoletimDTO> boletins = new ArrayList<>();

        for (Disciplina disciplina : disciplinas) {
            Nota notaDisciplina = notasAluno.stream().filter(na -> na.getDisciplina().equals(disciplina)).findFirst().orElse(null);

            boletins.add(BoletimDTO.builder()
                    .aluno(new AlunoDTO().converter(aluno))
                    .curso(new CursoDTO().converter(disciplina.getCurso()))
                    .disciplina(new DisciplinaDTO().converter(disciplina))
                    .nota(notaDisciplina != null ? notaDisciplina.getValor() : null)
                    .status(notaDisciplina != null ? (notaDisciplina.getValor() >= 7 ? Status.APROVADO : Status.REPROVADO) : Status.ATIVO)
                    .build());
        }

        return boletins;
    }

    private double obterNotaAlunoNaDisciplina(Aluno aluno, Disciplina disciplina) {
//        Optional<Nota> notaDoAlunoNaDisciplina = disciplina.getNotas().stream()
//                .filter(nota -> nota.getAluno().equals(aluno))
//                .findFirst();
//
//        return notaDoAlunoNaDisciplina.map(Nota::getValor).orElse(0.0);
        return 0;
    }
}


