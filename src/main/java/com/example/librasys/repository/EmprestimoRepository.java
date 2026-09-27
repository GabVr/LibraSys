package com.example.librasys.repository;


import com.example.librasys.model.Emprestimo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {


    boolean existsById(Integer id);
    List<Emprestimo> findAll();
    Optional<Emprestimo> findById(Integer id);
}
