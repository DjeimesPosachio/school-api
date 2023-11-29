package com.example.demo.service;

import com.example.demo.model.cadastro.Curso;
import com.example.demo.model.cadastro.Disciplina;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private CursoRepository cursoRepository;

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina getDisciplinaPorId(Long disciplinaId) {
        return disciplinaRepository.findById(disciplinaId).orElse(null);
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        validate(disciplina);
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina updateDisciplina(Long disciplinaId, Disciplina disciplinaAtualizada) {
        validate(disciplinaAtualizada);

        Disciplina disciplinaExistente = disciplinaRepository.findById(disciplinaId).orElse(null);
        if (disciplinaExistente != null) {
            disciplinaExistente.setNome(disciplinaAtualizada.getNome());

            return disciplinaRepository.save(disciplinaExistente);
        }
        return null;
    }

    public boolean deleteDisciplina(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElse(null);
        if (disciplina != null) {
            disciplinaRepository.delete(disciplina);
            return true;
        }
        return false;
    }

    private void validate(Disciplina disciplina) {
        if (isNull(disciplina))
            throw new RuntimeException("Disciplina não existe");

        if (isEmpty(disciplina.getNome()))
            throw new RuntimeException("Nome deve ser informado para cadastrar a disciplina");

        if (isNull(disciplina.getCurso()))
            throw new RuntimeException("Curso deve ser informado para cadastrar a disciplina");

        try {
            cursoRepository.findById(disciplina.getCurso().getId()).orElseThrow(() -> new Exception("Curso não encontrado. Id: " + disciplina.getCurso().getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}