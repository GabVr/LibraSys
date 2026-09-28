package com.example.librasys.service;

import com.example.librasys.model.Livro;
import com.example.librasys.repository.LivroRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LivroService {

    @Autowired
    LivroRepository livroRepository;


    public Livro salvarLivro(Livro livro) {
        validarLivro(livro);
        return livroRepository.save(livro);
    }

    public List<Livro> buscarTodosLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorId(Integer id){
        return livroRepository.findById(id).orElseThrow(() -> new AtributoException("Não foi possível encontrar o seu livro por id"));
    }

    public Livro buscarLivroPorTitulo(String titulo){
        return livroRepository.findbyTitulo(titulo).orElseThrow(() -> new AtributoException("Não foi possível encontrar o seu livro por titulo"));
    }

    public Livro atualizarLivroPorId(Integer id,  Livro livroDadosAtualizados) {
        Livro livroAtualizado = buscarLivroPorId(id);

        livroAtualizado.setTitulo(livroDadosAtualizados.getTitulo());
        livroAtualizado.setEditora(livroDadosAtualizados.getEditora());
        livroAtualizado.setCategoria(livroDadosAtualizados.getCategoria());

        return livroRepository.save(livroAtualizado);
    }

    public Livro deletarLivroPorId(int id){
        buscarLivroPorId(id);
        return livroRepository.deleteById(id);
    }

    public void validarLivro(Livro livro) {}
}
