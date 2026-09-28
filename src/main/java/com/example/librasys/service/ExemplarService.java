package com.example.librasys.service;


import com.example.librasys.model.Exemplar;
import com.example.librasys.model.Status;
import com.example.librasys.repository.ExemplarRepository;
import exceptions.AtributoException;
import exceptions.EnumException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExemplarService {

    @Autowired
    ExemplarRepository exemplarRepository;

    public Exemplar salvarExemplar(Exemplar exemplar){
        validarExemplar(exemplar);
        return exemplarRepository.save(exemplar);
    }

    public Exemplar buscarExemplarPorId(Integer idExemplar){
        return exemplarRepository.findById(idExemplar).orElseThrow(() -> new AtributoException("Não foi possível encontrar o exemplar por ID"));
    }

    public Exemplar buscarExemplarPorStatus(Status status){
        return exemplarRepository.findByStatus(status).orElseThrow(() -> new EnumException("Não foi possível encontrar o exemplar pelo status"));
    }

    public List<Exemplar> buscarTodosExemplares(){
        return exemplarRepository.findAll();
    }

    public Exemplar AtualizarExemplarPorId(Integer id,Exemplar exemplarDadosAtualizados){
        Exemplar exemplarAtualizado = buscarExemplarPorId(id);

        exemplarAtualizado.setCodigoPatrimonio(exemplarDadosAtualizados.getCodigoPatrimonio());
        exemplarAtualizado.setLivro(exemplarDadosAtualizados.getLivro());
        exemplarAtualizado.setStatus(exemplarDadosAtualizados.getStatus());

        return exemplarRepository.save(exemplarAtualizado);
    }

    public Exemplar deletarExemplarPorId(int id){
        buscarExemplarPorId(id);
        return exemplarRepository.deleteById(id);
    }

    public Exemplar deletarExemplarPorStatus(Status status){
        buscarExemplarPorStatus(status);
        return exemplarRepository.deleteByStatus(status);
    }

    public void validarExemplar(Exemplar exemplar) {}
}
