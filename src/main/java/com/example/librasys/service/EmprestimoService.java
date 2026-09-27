package com.example.librasys.service;

import com.example.librasys.model.Emprestimo;
import com.example.librasys.model.Situacao;
import com.example.librasys.repository.EmprestimoRepository;
import exceptions.AtributoException;
import org.springframework.beans.factory.annotation.Autowired;

public class EmprestimoService {

    @Autowired
    EmprestimoRepository emprestimoRepository;

    public Emprestimo salvarEmprestimo(Emprestimo emprestimo){
        validarEmprestimo(emprestimo);
        return emprestimoRepository.save(emprestimo);
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

    public void deletarEmprestimoPorId(Integer idEmprestimo) {
        buscarEmprestimoPorId(idEmprestimo);
        emprestimoRepository.deleteById(idEmprestimo);
    }

    public void deletarEmprestimoPorStatus(Situacao situacao) {
        buscarEmprestimoPorStatus(situacao);
        emprestimoRepository.deleteBySituacao(situacao);
    }

    public void validarEmprestimo(Emprestimo emprestimo) {}
}
