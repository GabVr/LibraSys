package com.example.librasys.controller;

import com.example.librasys.model.Livro;
import com.example.librasys.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Livro")
public class LivroController {

    @Autowired
    LivroService livroService;

    @PostMapping
    public ResponseEntity<?> inserirLivro(@Valid @RequestBody Livro livro) {

        Livro novoLivro = livroService.salvarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @GetMapping
    public ResponseEntity<?> listarLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.buscarTodosLivros());
    }

    @GetMapping("/id")
    public ResponseEntity<?> pegarLivroPorId(@Valid @RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.buscarLivroPorId(id));
    }

    @GetMapping("/titulo")
    public ResponseEntity<?> pegarLivroPorTitulo(@Valid @RequestParam String titulo) {
        return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarLivro(@Valid @RequestParam Integer id, @Valid @RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.atualizarLivroPorId(id, livro));
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarLivroPorId(@Valid @RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deletarLivroPorId(id));
    }
}
