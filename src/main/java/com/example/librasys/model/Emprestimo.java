package com.example.librasys.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public class Emprestimo {

    @NotNull
    private Integer id;

    @NotNull (message = "Você deve informar o usuário")
    private Usuario usuario;

    @NotNull (message = "Você deve informar o livro exemplar")
    private Exemplar exemplar;

    @Positive(message = "A data de emprestimo não pode ser 0 ou negativa")
    @NotNull
    private LocalDate dataEmprestimo;

    @Positive(message = "A data de devolução não pode ser 0 ou negativa")
    @NotNull
    private LocalDate dataDevolucao;

    @Positive(message = "A data prevista de devolução não pode ser 0 ou negativa")
    @NotNull
    private LocalTime dataPrevistaDevolucao;

    private Situacao status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalTime getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
}
