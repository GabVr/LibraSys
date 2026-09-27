package com.example.librasys.service;

import com.example.librasys.model.Autor;
import com.example.librasys.repository.AutorRepository;
import exceptions.AtributoException;
import exceptions.DataException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public static void dataNascimento(LocalDate dataNascimento) {

        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new DataException("Data de nascimento invalido, não pode ser posterior a data atual");
        }
    }

    public Autor salvarAutor(Autor autor) {
        validarAutor(autor);
        return autorRepository.save(autor);
    }

    public Autor buscarAutorPorId(Integer idAutor) {
        return autorRepository.findById(idAutor).orElseThrow(() -> new AtributoException("Autor inexistente"));
    }

    public Autor buscarAutorPorNome(String nomeAutor) {
        return autorRepository.findByNome(nomeAutor).orElseThrow(() -> new AtributoException("Autor inexistente"));
    }

    public List<Autor> buscarTodosAutores() {
        return autorRepository.findAll();
    }

    public Autor atualizarAutor(Integer id,Autor autorAtualizado) {
        Autor autorAtualizadoSalvo = buscarAutorPorId(id);

        autorAtualizadoSalvo.setNome(autorAtualizado.getNome());
        autorAtualizadoSalvo.setNome(autorAtualizado.getNacionalidade());

        return autorRepository.save(autorAtualizadoSalvo);
    }

    public void deletarAutorPorId(Integer idAutor) {
        buscarAutorPorId(idAutor);
        autorRepository.deleteById(idAutor);
    }

    public void validarAutor(Autor autor) {

        dataNascimento(autor.getDataNascimento());

    }
}
