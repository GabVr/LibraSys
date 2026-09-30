package com.example.librasys.controller;

import com.example.librasys.model.Usuario;
import com.example.librasys.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuario());
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarUsuarioPorId(@Valid @RequestParam Integer id,@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarUsuarioPorId(@Valid @RequestParam Integer id, @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuario));
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarUsuario(@Valid @RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.deletarUsuarioPorId(id));
    }
}
