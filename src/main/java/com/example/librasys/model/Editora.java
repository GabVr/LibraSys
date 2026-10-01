package com.example.librasys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="editoras")
public class Editora {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome", nullable=false)
    @NotBlank (message = "Digite o nome da editora")
    private String nome;

    @Column(name="cidade", nullable=false)
    @NotBlank (message = "É importante você informar a cidade")
    private String cidade;

    @Column(name="email", nullable=false, unique=true)
    @Email (message = "O formato do email está equivacado")
    @NotBlank (message = "Informe o campo de email")
    private String email;
}
