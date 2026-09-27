package com.example.librasys.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class Autor {

    @NotNull
    private Integer id;

    @NotBlank (message = "Você deve informar o nome")
    private String nome;

    @NotBlank (message = "Você deve informar a nacionalidade")
    private String nacionalidade;

    @Positive
    @NotBlank (message = "Você deve informar a data de nascimento")
    private LocalDate dataNascimento;


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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
