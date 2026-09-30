package com.example.librasys.repository;

import com.example.librasys.model.Exemplar;
import com.example.librasys.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {

    Optional<Exemplar> findById(Integer id);
    boolean existsById(Integer id);
    List<Exemplar> findAll();
    Optional<Exemplar> findByStatus(Status status);

    Exemplar deleteByStatus(Status status);
    Exemplar deleteById(int id);
    long countByStatus(Status status);
}
