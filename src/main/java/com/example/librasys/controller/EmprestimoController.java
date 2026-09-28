package com.example.librasys.controller;

import com.example.librasys.model.Emprestimo;
import com.example.librasys.model.Situacao;
import com.example.librasys.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Emprestimo")
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @PutMapping
    public ResponseEntity<?> inserirEmprestimo(@Valid @RequestBody Emprestimo emprestimo){
        Emprestimo novoEmprestimo = emprestimoService.salvarEmprestimo(emprestimo);
        return ResponseEntity.ok(emprestimoService.salvarEmprestimo(novoEmprestimo));
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosEmprestimos(){
        return ResponseEntity.ok(emprestimoService.buscarTodosEmprestimos());
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarEmprestimoPorId(@Valid @RequestParam Integer idEmprestimo){
        return ResponseEntity.ok(emprestimoService.buscarEmprestimoPorId(idEmprestimo));
    }

    @GetMapping("/situacao")
    public ResponseEntity<?> buscarEmprestimoSituacao(@Valid @RequestParam Situacao situacao){
        return ResponseEntity.ok(emprestimoService.buscarEmprestimoPorStatus(situacao));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarEmprestimo(@Valid @RequestParam Integer id,@Valid @RequestBody Emprestimo emprestimo){
        return ResponseEntity.ok(emprestimoService.atualizarEmprestimoPorId(id,emprestimo));
    }

    @DeleteMapping("/deletarId")
    public  ResponseEntity<?> deletarEmprestimoPorId( @Valid @RequestParam Integer id){
        return ResponseEntity.ok(emprestimoService.deletarEmprestimoPorId(id));
    }

    @DeleteMapping("/deletarStatus")
    public  ResponseEntity<?> deletarEmprestimoPorStatus(@Valid @RequestParam Situacao situacao){
        return ResponseEntity.ok(emprestimoService.deletarEmprestimoPorStatus(situacao));
    }
}
