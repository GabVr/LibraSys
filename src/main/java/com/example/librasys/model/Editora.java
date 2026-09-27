package com.example.librasys.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Editora {

    @NotNull
    private Integer id;

    @NotBlank (message = "Digite o nome da editora")
    private String nome;

    @NotBlank
    private String cidade;

    @Email (message = "O formato do email está equivacado")
    @NotBlank (message = "Informe o campo de email")
    private String email;


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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
