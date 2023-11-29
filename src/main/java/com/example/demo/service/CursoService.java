package com.example.demo.service;

import com.example.demo.model.cadastro.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso createCurso(Curso curso) {
        validate(curso);
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Curso curso) {
        validate(curso);
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

    private void validate(Curso curso) {
        if (isNull(curso))
            throw new RuntimeException("Curso não existe");

        if (isEmpty(curso.getNome()))
            throw new RuntimeException("Nome deve ser informado para cadastrar o curso");
    }
}