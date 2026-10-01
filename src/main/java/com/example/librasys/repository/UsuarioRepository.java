package com.example.librasys.repository;

import com.example.librasys.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsById(Integer id);
    boolean existsByNome(String nome);

    @Override
    Optional<Usuario> findById(Integer id);

    @Override
    List<Usuario> findAll();
    List<Usuario>findByAtivo(boolean ativo);
    Usuario deleteById(int id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByCpf(String cpf);
}
