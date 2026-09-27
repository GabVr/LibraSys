package com.example.librasys.service;

import com.example.librasys.model.Usuario;
import com.example.librasys.repository.UsuarioRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario){
        validarUsuario(usuario);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuario(){

        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Integer id){
        return usuarioRepository.findById(id).orElseThrow(() -> new AtributoException("Não foi possível encontrar o usuário por ID"));
    }

    public Usuario atualizarUsuario(Integer id,Usuario usuarioAtualizado){

        Usuario usuarioAtualizadoSalvo = buscarUsuarioPorId(id);

        usuarioAtualizadoSalvo.setNome(usuarioAtualizado.getNome());
        usuarioAtualizadoSalvo.setEmail(usuarioAtualizado.getEmail());
        usuarioAtualizadoSalvo.setSenha(usuarioAtualizado.getSenha());
        usuarioAtualizadoSalvo.setTelefone(usuarioAtualizado.getTelefone());
        usuarioAtualizadoSalvo.setAtivo(usuarioAtualizado.isAtivo());

        return usuarioRepository.save(usuarioAtualizadoSalvo);
    }

    public void deletarUsuarioPorId(Integer id){
        buscarUsuarioPorId(id);
        usuarioRepository.deleteById(id);
    }

    public void validarUsuario(Usuario usuario) {}
}
