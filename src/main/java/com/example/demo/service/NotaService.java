package com.example.demo.service;

import com.example.demo.model.negocio.Nota;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Nota> getAllNotas() {
        return notaRepository.findAll();
    }

    public Nota createNota(Nota nota) {
        validate(nota);
        return notaRepository.save(nota);
    }

    public Nota getNotaPorId(Long notaId) {
        return notaRepository.findById(notaId).orElse(null);
    }

    public Nota updateNota(Long notaId, Nota notaAtualizada) {
        Nota notaExistente = notaRepository.findById(notaId).orElse(null);
        if (notaExistente != null) {
            if (notaAtualizada.getValor() >= 0 && notaAtualizada.getValor() <= 10) {
                notaExistente.setValor(notaAtualizada.getValor());

                return notaRepository.save(notaExistente);
            }
            // Implemente lógica para lidar com a nota inválida, se necessário
        }
        return null;
    }

    public boolean deleteNota(Long notaId) {
        Nota nota = notaRepository.findById(notaId).orElse(null);
        validate(nota);
        notaRepository.delete(nota);
        return true;
    }

    private void validate(Nota nota) {
        if (isNull(nota))
            throw new RuntimeException("Nota não cadastrada");

        if (isNull(nota.getValor()))
            throw new RuntimeException("Valor da nota deve ser informada");

        if (nota.getValor() < 0 || nota.getValor() > 10)
            throw new RuntimeException("Valor da nota deve ser positivo e até 10");

        if (notaRepository.existsByAlunoAndDisciplina(nota.getAluno(), nota.getDisciplina()))
            throw new RuntimeException("Nota já informada para o aluno e disciplina");
    }
}
