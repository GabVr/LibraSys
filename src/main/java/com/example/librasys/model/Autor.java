package com.example.librasys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();


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

    public Set<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }
}
