package com.example.demo.controller;

import com.example.demo.model.negocio.Nota;
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
    public ResponseEntity<Nota> getNotaPorId(@PathVariable Long notaId) {
        Nota nota = notaService.getNotaPorId(notaId);
        return ResponseEntity.ok(nota);
    }

    @PutMapping("/{notaId}")
    public ResponseEntity<Nota> updateNota(
            @PathVariable Long notaId,
            @RequestBody Nota notaAtualizada
    ) {
        Nota nota = notaService.updateNota(notaId, notaAtualizada);
        return ResponseEntity.ok(nota);
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
