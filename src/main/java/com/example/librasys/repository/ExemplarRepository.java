package com.example.librasys.repository;

import com.example.librasys.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {

    Optional<Exemplar> findById(Integer id);
    boolean existsById(Integer id);
    List<Exemplar> findAll();
}
