package com.example.librasys.service;

import com.example.librasys.model.Usuario;
import com.example.librasys.repository.UsuarioRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario salvarUsuario(Usuario usuario){


        usuario.setCpf(limparCpf(usuario.getCpf()));

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    private String limparCpf(String cpf) {
        if (cpf == null) {
            return null;
        }

        return cpf.replaceAll("\\D", "");
    }

    public List<Usuario> listarUsuario(){

        return usuarioRepository.findAll();
    }

    public  List<Usuario> listarAtivos(){
        return usuarioRepository.findByAtivo(true);
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

}
