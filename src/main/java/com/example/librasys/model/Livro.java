package com.example.librasys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;

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
    @NotNull (message = "O ano de publicação não pode ser nula/vazia")
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
    private Set<Autor> autores = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }
}
