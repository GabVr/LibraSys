package com.example.librasys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome",nullable=false)
    @NotBlank (message = "Você deve informar o nome")
    private String nome;

    @Column(name="nacionalidade", nullable=false)
    @NotBlank (message = "Você deve informar a nacionalidade")
    private String nacionalidade;

    @Column(name="data_nascimento", nullable=false)
    @NotNull(message = "Você deve informar a data de nascimento")
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
