package com.example.demo.repository;

import com.example.demo.model.cadastro.Aluno;
import com.example.demo.model.cadastro.Disciplina;
import com.example.demo.model.negocio.Nota;
import com.example.demo.response.AlunoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    boolean existsByAlunoAndDisciplina(Aluno aluno, Disciplina disciplina);

    List<Nota> findByAluno(Aluno aluno);
}