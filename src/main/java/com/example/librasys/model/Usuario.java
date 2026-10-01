package com.example.librasys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome", nullable = false)
    @NotBlank(message = "O campo de nome não pode ficar vazio")
    private String nome;

    @Column(name="email", nullable = false, unique = true)
    @NotBlank(message = "O campo de email deve ser preenchido")
    @Email (message = "O formato do email está equivocado")
    private String email;

    @Column(name="senha", nullable = false, unique = true)
    @NotBlank(message = "O campo de senha deve ser preenchido")
    private String senha;

    @Column(name="cpf", nullable = false, unique = true)
    @CPF (message = "O formato do cpf está equivocado")
    @NotBlank (message = "O campo de cpf deve ser preenchido")
    private String cpf;

    @Column(name="telefone", nullable = false, unique = true)
    @NotBlank (message = "Você deve preencher o campo de telefone")
    @Pattern(regexp = "^\\d{2}9?\\d{8}$")
    private String telefone;

    @Column(name="ativo")
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo;
}
