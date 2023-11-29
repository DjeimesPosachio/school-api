package com.example.demo.service;

import com.example.demo.model.cadastro.Aluno;
import com.example.demo.model.cadastro.CursoAluno;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.CursoRepository;
import com.example.demo.request.AlunoRequest;
import com.example.demo.response.AlunoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<AlunoResponse> getAllAlunos() {
        return alunoRepository.findAll().stream().map(this::converterToResponse).collect(Collectors.toList());
    }

    public AlunoResponse createAluno(AlunoRequest alunoRequest) {
        Aluno aluno = Aluno.builder()
                .nome(alunoRequest.getNome())
                .build();

        List<CursoAluno> cursosDoAluno = alunoRequest.getCursosId().stream().map(cursoId -> CursoAluno.builder()
            .aluno(aluno)
            .curso(cursoRepository.findById(cursoId).get())
            .build())
                .collect(Collectors.toList());

        aluno.setCursos(cursosDoAluno);

        return converterToResponse(alunoRepository.save(aluno));
    }

    public AlunoResponse getAlunoById(Long id) {
        return converterToResponse(alunoRepository.findById(id).get());
    }

    public AlunoResponse updateAluno(Long alunoId, Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(alunoId).orElse(null);
        if (alunoExistente != null) {
            alunoExistente.setNome(alunoAtualizado.getNome());
//            alunoExistente.setCurso(alunoAtualizado.getCurso());
            return converterToResponse(alunoRepository.save(alunoExistente));
        }
        return null;
    }

    public boolean deleteAluno(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId).orElse(null);
        if (aluno != null) {
            alunoRepository.delete(aluno);
            return true;
        }
        return false;
    }

    private AlunoResponse converterToResponse(Aluno aluno) {
        return AlunoResponse.builder()
                .nome(aluno.getNome())
                .cursos(aluno.getCursos().stream().map(CursoAluno::getCurso).collect(Collectors.toList()))
                .build();
    }
}
