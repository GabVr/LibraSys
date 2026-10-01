package com.example.librasys.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
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

    @Column(name="data_emprestimo", nullable=true)
    private LocalDate dataEmprestimo;


    @Column(name="data_devolucao", nullable = true)
    private LocalDate dataDevolucao;

    @Column(name="data_prevista_devolucao", nullable=false)
    @NotNull(message = "É necessário ter a data prevista para a devolução")
    private LocalDate dataPrevistaDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_emprestimo")
    private Situacao situacao;
}
