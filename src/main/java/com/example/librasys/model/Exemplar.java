package com.example.librasys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="exemplares")
public class Exemplar {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_livro")
    @NotNull (message ="O campo de livro não pode ficar vazio")
    private Livro livro;

    @Column(name="codigo_patrimonio", nullable=false, unique=true)
    @NotBlank (message = "Não pode deixar o campo vazio")
    @Pattern(regexp = "^EX\\d{6}$",
             message = "O código deve seguir o formato EX000000")
    private String codigoPatrimonio;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_exemplares")
    private Status status;
}
