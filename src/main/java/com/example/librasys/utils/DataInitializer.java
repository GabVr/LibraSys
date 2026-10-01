package com.example.librasys.utils;

import com.example.librasys.model.TipoUsuario;
import com.example.librasys.model.Usuario;
import com.example.librasys.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (usuarioRepository.findByEmail("admin@librasys.com").isEmpty()) {

            Usuario admin = new Usuario();

            admin.setNome("Administrador");
            admin.setCpf("99888125079");
            admin.setEmail("admin@librasys.com");
            admin.setTelefone("81999999999");

            admin.setSenha(
                    passwordEncoder.encode("admin123")
            );

            admin.setAtivo(true);
            admin.setTipo(TipoUsuario.ADMIN);

            usuarioRepository.save(admin);
        }
    }
}