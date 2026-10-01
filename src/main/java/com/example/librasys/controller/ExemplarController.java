package com.example.librasys.controller;

import com.example.librasys.model.Exemplar;
import com.example.librasys.model.Livro;
import com.example.librasys.model.Status;
import com.example.librasys.service.ExemplarService;
import com.example.librasys.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exemplares")
public class ExemplarController {

    @Autowired
    ExemplarService exemplarService;

    @Autowired
    LivroService livroService;

    @PostMapping("/salvar")
    public ResponseEntity<Void> salvarExemplar(
            @Valid   @RequestParam String codigoPatrimonio,
            @Valid   @RequestParam Integer livroId,
            @Valid   @RequestParam Status status) {

        Livro livro = livroService.buscarLivroPorId(livroId);

        Exemplar exemplar = new Exemplar();
        exemplar.setCodigoPatrimonio(codigoPatrimonio);
        exemplar.setLivro(livro);
        exemplar.setStatus(status);

        exemplarService.salvarExemplar(exemplar);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/exemplares")
                .build();
    }

    @PostMapping
    public ResponseEntity<?> inserirExemplar(@RequestBody Exemplar exemplar) {
        Exemplar novoExemplar = exemplarService.salvarExemplar(exemplar);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoExemplar);
    }

    @GetMapping
    public ResponseEntity<?> listarExemplar() {
        return ResponseEntity.status(HttpStatus.OK).body(exemplarService.buscarTodosExemplares());
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarExemplarPorId(@Valid @RequestParam Integer id) {
        return ResponseEntity.ok(exemplarService.buscarExemplarPorId(id));
    }

    @GetMapping("/status")
    public ResponseEntity<?> buscarExemplarPorStatus(@Valid @RequestParam Status status) {
        return ResponseEntity.ok(exemplarService.buscarExemplarPorStatus(status));
    }

    @PutMapping("/atualizar")
    public  ResponseEntity<?> atualizarPorId(@Valid @RequestParam Integer id,@Valid @RequestBody Exemplar exemplar) {
        return ResponseEntity.ok(exemplarService.AtualizarExemplarPorId(id, exemplar));
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarExemplarPorId(@Valid @RequestParam Integer id) {
        return ResponseEntity.ok(exemplarService.deletarExemplarPorId(id));
    }

    @DeleteMapping("/deletarStatus")
    public ResponseEntity<?> deletarExemplarPorStatus(@Valid @RequestParam Status status) {
        return ResponseEntity.ok(exemplarService.deletarExemplarPorStatus(status));
    }
}
