package com.example.librasys.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class Usuario {

    @NotNull
    private Integer id;

    @NotBlank(message = "O campo de nome não pode ficar vazio")
    private String nome;

    @NotBlank(message = "O campo de email deve ser preenchido")
    @Email (message = "O formato do email está equivocado")
    private String email;

    @NotBlank(message = "O campo de senha deve ser preenchido")
    private String senha;

    @CPF (message = "O formato do cpf está equivocado")
    @NotBlank (message = "O campo de cpf deve ser preenchido")
    private String cpf;

    @NotBlank (message = "Você deve preencher o campo de telefone")
    private String telefone;
    private boolean ativo;

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
}
