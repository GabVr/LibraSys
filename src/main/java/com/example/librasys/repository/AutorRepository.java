package com.example.librasys.repository;

import com.example.librasys.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Optional<Autor> findByNome(String nome);
    Optional<Autor> findById(Integer id);
    boolean existByCpf(String cpf);
    boolean existsById(Integer id);

    @Override
    List<Autor> findAll();

    @Override
    void deleteById(Integer id);
}
