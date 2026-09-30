package com.example.librasys.repository;


import com.example.librasys.model.Editora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer> {

    Optional<Editora> findByNome(String nome);
    Editora deleteById(int id);
    boolean existsByNome(String nome);
    List<Editora> findAll();
    Optional<Editora> findById(Integer id);

}
