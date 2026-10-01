package com.example.librasys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
}
