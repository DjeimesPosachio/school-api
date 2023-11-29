package com.example.demo.service;

import com.example.demo.model.Nota;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (nota.getValor() >= 0 && nota.getValor() <= 10) {
            return notaRepository.save(nota);
        }
        // Implemente lógica para lidar com a nota inválida, se necessário
        return null;
    }

    public Nota getNotaPorId(Long notaId) {
        return notaRepository.findById(notaId).orElse(null);
    }

    public List<Nota> getTodasNotas() {
        return notaRepository.findAll();
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
        if (nota != null) {
            notaRepository.delete(nota);
            return true;
        }
        return false;
    }
}
