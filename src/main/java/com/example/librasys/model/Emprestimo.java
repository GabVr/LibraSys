package com.example.librasys.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name="emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @NotNull (message = "Você deve informar o usuário")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_exemplar")
    @NotNull (message = "Você deve informar o livro exemplar")
    private Exemplar exemplar;

    @Column(name="data_emprestimo", nullable=false)
    @Positive(message = "A data de emprestimo não pode ser 0 ou negativa")
    @NotNull
    private LocalDate dataEmprestimo;

    @Column(name="data_devolucao", nullable=false)
    @Positive(message = "A data de devolução não pode ser 0 ou negativa")
    @NotNull
    private LocalDate dataDevolucao;

    @Column(name="data_prevista_devolucao", nullable=false)
    @Positive(message = "A data prevista de devolução não pode ser 0 ou negativa")
    @NotNull(message = "É necessário ter a data prevista para a devolução")
    private LocalDate dataPrevistaDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_emprestimo")
    private Situacao situacao;

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

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Situacao getStatus() {
            return  situacao;
    }

    public void setStatus(Situacao situacao) {
        this.situacao = situacao;
    }
}
