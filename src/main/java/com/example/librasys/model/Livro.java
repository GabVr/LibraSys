package com.example.librasys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="titulo", nullable=false)
    @NotBlank(message = "Você deve informar o título do livro")
    private String titulo;

    @Column(name="isbn", nullable=false, unique=true)
    @NotBlank(message = "Você deve informar o isbn")
    private String isbn;

    @Column(name="categoria", nullable=false)
    @NotBlank(message = "Você deve informar a categoria")
    private String categoria;

    @Column(name="ano_publicacao", nullable=false)
    @Positive (message = "O ano de publicação não pode ser um número nulo ou negativo")
    private int anoPublicacao;

    @ManyToOne
    @JoinColumn(name = "id_editora")
    @NotNull (message = "O campo de editor não pode ficar vazio")
    private Editora editora;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @NotNull (message = "O campo do autor não pode ficar vazio")
    private Set<Autor> autores = new HashSet<>();
}
