package com.example.librasys.repository;

import com.example.librasys.model.Autor;
import com.example.librasys.model.Editora;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Integer> {

    Optional<Editora> findByNome(String nome);
    Editora deleteById(int id);
    boolean existsByNome(String nome);
    List<Editora> findAll();
    Optional<Editora> findById(Integer id);

}
