package com.example.librasys.controller;

import com.example.librasys.model.Editora;
import com.example.librasys.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/editoras")
public class EditoraController {

    @Autowired
    EditoraService editoraService;

    @PostMapping
    public ResponseEntity<?> inserirEditora(@Valid @RequestBody Editora editora){
        Editora novoEditora = editoraService.cadastrarEditora(editora);
        return ResponseEntity.ok(editoraService.cadastrarEditora(novoEditora));
    }

    @GetMapping
    public ResponseEntity<?> listarEditoras(){
        return ResponseEntity.ok(editoraService.BuscarTodasEditoras());
    }

    @GetMapping("/nome")
    public ResponseEntity<?> buscarEditoraNome(@Valid @RequestParam String nome){
        return ResponseEntity.ok(editoraService.buscarPorNome(nome));
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarEditoraId(@Valid @RequestParam Integer id){
        return ResponseEntity.ok(editoraService.buscarPorId(id));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarEditora(@Valid @RequestParam Integer id,@Valid @RequestBody Editora editora){
        return ResponseEntity.ok(editoraService.atualizarEditoraPorId(id,editora));
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarEditora(@Valid @RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(editoraService.deletarEditoraPorId(id));
    }
}
