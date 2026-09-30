package com.example.librasys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

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
    private String telefone;

    @Column(name="ativo")
    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
