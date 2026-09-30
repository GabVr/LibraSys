package com.example.librasys.repository;

import com.example.librasys.model.Livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findAll();
    Optional<Livro> findById(Integer id);
    Optional<Livro> findByTituloContainingIgnoreCase(String titulo);
    Livro deleteById(int id);
}
