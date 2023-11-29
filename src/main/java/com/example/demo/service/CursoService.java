package com.example.demo.service;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Curso> optionalCurso = cursoRepository.findById(cursoId);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            cursoRepository.delete(curso);
            return true;
        }
        return false;
    }


    public Curso getCursoPorId(Long cursoId) {
        return cursoRepository.findById(cursoId).orElse(null);
    }

}