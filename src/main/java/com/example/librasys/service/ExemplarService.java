package com.example.librasys.service;


import com.example.librasys.model.Exemplar;
import com.example.librasys.model.Status;
import com.example.librasys.repository.ExemplarRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ExemplarService {

    @Autowired
    ExemplarRepository exemplarRepository;

    public Exemplar salvarExemplar(Exemplar exemplar){
        return exemplarRepository.save(exemplar);
    }

    public Exemplar buscarExemplarPorId(Integer idExemplar){
        return exemplarRepository.findById(idExemplar).orElseThrow(() -> new AtributoException("Não foi possível encontrar o exemplar por ID"));
    }

    public List<Exemplar> buscarExemplarPorStatus(Status status){
        return exemplarRepository.findByStatus(status);
    }

    public List<Exemplar> buscarExemplaresDisponiveis(){
        return exemplarRepository.findByStatus(Status.DISPONIVEL);
    }

    public long contarExemplares() {
        return exemplarRepository.count();
    }

    public long contarDisponiveis() {
        return exemplarRepository.countByStatus(Status.DISPONIVEL);
    }

    public long contarEmprestados() {
        return exemplarRepository.countByStatus(Status.EMPRESTADO);
    }

    public long contarEmManutencao() {
        return exemplarRepository.countByStatus(Status.MANUTENCAO);
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
}
