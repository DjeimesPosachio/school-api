package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public boolean deleteCurso(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId).orElse(null);
        if (curso != null) {
            cursoRepository.delete(curso);
            return true;
        }
        return false;
    }

    public Curso getCursoPorId(Long cursoId) {
        return cursoRepository.findById(cursoId).orElse(null);
    }
}