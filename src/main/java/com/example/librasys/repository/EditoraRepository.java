package com.example.librasys.repository;

import com.example.librasys.model.Editora;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditoraRepository extends JpaRepository<Editora, Integer> {

    List<Editora> findByNome(String nome);
    void deleteById(Integer id);
    boolean existsByNome(String nome);
    List<Editora> findAll();
}
