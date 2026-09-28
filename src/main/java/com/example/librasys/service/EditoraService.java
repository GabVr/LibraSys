package com.example.librasys.service;

import com.example.librasys.model.Editora;
import com.example.librasys.repository.EditoraRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class EditoraService {

    @Autowired
    EditoraRepository editoraRepository;

    public Editora cadastrarEditora(Editora editora){
        validarEditora(editora);
        return editoraRepository.save(editora);
    }

    public List<Editora> BuscarTodasEditoras() {
        return editoraRepository.findAll();
    }

    public Editora buscarPorNome(String nome) {
        return editoraRepository.findByNome(nome).orElseThrow(() -> new AtributoException("Não foi possível encontrar editora pelo nome"));
    }

    public Editora buscarPorId(Integer id) {
        return editoraRepository.findById(id).orElseThrow(() -> new AtributoException("Não foi possível encontrar editora pelo id"));
    }

    public Editora atualizarEditoraPorId(Integer id, Editora editoraDadosAtualizados){
        Editora editoraAtualizada =  buscarPorId(id);

        editoraAtualizada.setNome(editoraDadosAtualizados.getNome());
        editoraAtualizada.setCidade(editoraDadosAtualizados.getCidade());
        editoraAtualizada.setEmail(editoraDadosAtualizados.getEmail());

        return  editoraRepository.save(editoraAtualizada);
    }

    public Editora deletarEditoraPorId(int id) {
        buscarPorId(id);
        return editoraRepository.deleteById(id);
    }

    public void validarEditora(Editora editora) {}
}
