package com.example.librasys.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Livro {

    @NotNull
    private Integer id;

    @NotBlank(message = "Você deve informar o título do livro")
    private String titulo;

    @NotBlank(message = "Você deve informar o isbn")
    private String isbn;

    @NotBlank(message = "Você deve informar a categoria")
    private String categoria;

    @Positive (message = "O ano de publicação não pode ser um número nulo ou negativo")
    @NotNull (message = "O ano de publicação não pode ser nula/vazia")
    private int anoPublicacao;

    @NotNull (message = "O campo de editor não pode ficar vazio")
    private Editora editora;

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
}
