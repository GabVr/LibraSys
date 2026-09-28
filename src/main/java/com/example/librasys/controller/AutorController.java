package com.example.librasys.controller;

import com.example.librasys.model.Autor;
import com.example.librasys.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Autor")
public class AutorController {

    @Autowired
    AutorService autorService;


    @PostMapping
    public ResponseEntity<?> inserirAutor(@Valid @RequestBody Autor autor){
        Autor novoAutor = autorService.salvarAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAutor);
    }

    @GetMapping
    public ResponseEntity<?> listarAutores(){
        return ResponseEntity.status(HttpStatus.OK).body(autorService.buscarTodosAutores());
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarAutorPorId(@Valid @RequestParam Integer idAutor){
        return ResponseEntity.status(HttpStatus.OK).body(autorService.buscarAutorPorId(idAutor));
    }

    @GetMapping("/nome")
    public ResponseEntity<?> buscarAutorNome(@Valid @RequestParam String nomeAutor){
        return ResponseEntity.status(HttpStatus.OK).body(autorService.buscarAutorPorNome(nomeAutor));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarAutor(@Valid @RequestParam Integer idAutor,@Valid @RequestBody Autor autor){
        return ResponseEntity.ok(autorService.atualizarAutor(idAutor,autor));
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarAutor(@Valid @RequestParam Integer idAutor){
        return ResponseEntity.status(HttpStatus.OK).body(autorService.deletarAutorPorId(idAutor));
    }
}
