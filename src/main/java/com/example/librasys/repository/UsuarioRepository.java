package com.example.librasys.repository;

import com.example.librasys.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsById(Integer id);
    boolean existsByNome(String nome);

    @Override
    Optional<Usuario> findById(Integer id);

    @Override
    List<Usuario> findAll();
    Optional<Usuario>findAtivos(boolean ativos);
    Usuario deleteById(int id);
}
