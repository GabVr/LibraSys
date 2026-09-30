package com.example.librasys.service;

import com.example.librasys.model.Usuario;
import com.example.librasys.repository.UsuarioRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Usuario usuario;

    public Usuario salvarUsuario(Usuario usuario){
        validarUsuario(usuario);

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuario(){

        return usuarioRepository.findAll();
    }

    public Optional<Usuario> listarAtivos(){

        if(usuario.isAtivo()){
            return usuarioRepository.findAtivos(usuario.isAtivo());
        }
        else{
            throw new NoSuchElementException("Não foi possível encontrar o usuário");
        }
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

    public Usuario deletarUsuarioPorId(int id){
        buscarUsuarioPorId(id);
        return usuarioRepository.deleteById(id);
    }

    public void validarUsuario(Usuario usuario) {}
}
