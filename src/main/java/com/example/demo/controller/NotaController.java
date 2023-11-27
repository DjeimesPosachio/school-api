package com.example.demo.controller;

import com.example.demo.model.Nota;
import com.example.demo.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @GetMapping
    public List<Nota> getAllNotas() {
        return notaService.getAllNotas();
    }

    @PostMapping
    public Nota createNota(@RequestBody Nota nota) {
        return notaService.createNota(nota);
    }

    @GetMapping("/{notaId}")
    public ResponseEntity<Nota> buscarNotaPorId(@PathVariable Long notaId) {
        Nota nota = notaService.getNotaPorId(notaId);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{notaId}")
    public ResponseEntity<Nota> atualizarNota(
            @PathVariable Long notaId,
            @RequestBody Nota notaAtualizada
    ) {
        Nota nota = notaService.updateNota(notaId, notaAtualizada);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{notaId}")
    public ResponseEntity<Void> excluirNota(@PathVariable Long notaId) {
        boolean excluiu = notaService.deleteNota(notaId);
        if (excluiu) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
