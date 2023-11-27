package com.example.demo.service;

import com.example.demo.model.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno createAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno updateAluno(Long alunoId, Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(alunoId).orElse(null);
        if (alunoExistente != null) {
            alunoExistente.setNome(alunoAtualizado.getNome());
            alunoExistente.setCurso(alunoAtualizado.getCurso());
            return alunoRepository.save(alunoExistente);
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

}
