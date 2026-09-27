package com.example.librasys.repository;

import com.example.librasys.model.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    List<Livro> findAll();
    Optional<Livro> findById(Integer id);
    Optional<Livro>  findbyTitulo(String titulo);

    @Override
    void deleteById(Integer id);
}
