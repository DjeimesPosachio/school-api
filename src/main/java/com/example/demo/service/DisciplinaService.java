package com.example.demo.service;

import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina getDisciplinaPorId(Long disciplinaId) {
        return disciplinaRepository.findById(disciplinaId).orElse(null);
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina updateDisciplina(Long disciplinaId, Disciplina disciplinaAtualizada) {
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

}