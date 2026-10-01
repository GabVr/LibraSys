package com.example.librasys.service;

import com.example.librasys.model.Emprestimo;
import com.example.librasys.model.Situacao;
import com.example.librasys.repository.EmprestimoRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class EmprestimoService {

    @Autowired
    EmprestimoRepository emprestimoRepository;

    public Emprestimo salvarEmprestimo(Emprestimo emprestimo){
        validarEmprestimo(emprestimo);
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolverEmprestimo(Integer id, LocalDate dataDevolucao) {

        Emprestimo emprestimo = buscarEmprestimoPorId(id);

        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setStatus(Situacao.DEVOLVIDO);

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> buscarTodosEmprestimos(){
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarEmprestimoPorId(Integer idEmprestimo) {
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(() -> new AtributoException("Emprestimo inexistente"));
    }

    public Emprestimo buscarEmprestimoPorStatus(Situacao situacao) {
        return emprestimoRepository.findBySituacao(situacao).orElseThrow(() -> new AtributoException("Não foi possível encontrar"));
    }

    public Emprestimo  atualizarEmprestimoPorId(Integer id,Emprestimo emprestimoDadosAtualizados){
        Emprestimo EmprestimoAtualizado = buscarEmprestimoPorId(id);

        EmprestimoAtualizado.setExemplar(emprestimoDadosAtualizados.getExemplar());
        EmprestimoAtualizado.setDataEmprestimo(emprestimoDadosAtualizados.getDataEmprestimo());
        EmprestimoAtualizado.setDataDevolucao(emprestimoDadosAtualizados.getDataDevolucao());
        EmprestimoAtualizado.setStatus(emprestimoDadosAtualizados.getStatus());

        return emprestimoRepository.save(EmprestimoAtualizado);
    }


    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public long contarAtivos() {
        return emprestimoRepository.countBySituacao(Situacao.ATIVO);
    }

    public long contarAtrasados() {
        return emprestimoRepository.countBySituacao(Situacao.ATRASADO);
    }

    public List<Emprestimo> listarRecentes() {
        return emprestimoRepository.findTop5ByOrderByDataEmprestimoDesc();
    }

    public Emprestimo deletarEmprestimoPorId(int idEmprestimo) {
        buscarEmprestimoPorId(idEmprestimo);
        return emprestimoRepository.deleteById(idEmprestimo);
    }

    public Emprestimo deletarEmprestimoPorStatus(Situacao situacao) {
        buscarEmprestimoPorStatus(situacao);
        return emprestimoRepository.deleteBySituacao(situacao);
    }

    public void validarEmprestimo(Emprestimo emprestimo) {}
}
