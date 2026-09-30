package com.example.librasys.repository;

import com.example.librasys.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findByNome(String nome);
    Optional<Autor> findById(Integer id);;
    boolean existsById(Integer id);

    @Override
    List<Autor> findAll();

    Autor deleteById(int id);
}
