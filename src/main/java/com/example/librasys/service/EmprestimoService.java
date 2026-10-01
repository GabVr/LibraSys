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


    public static void validarDataEmprestimo(LocalDate dataEmprestimo){

        if(dataEmprestimo.isAfter(LocalDate.now())){
            throw new AtributoException("A data emprestimo deve ser posterior a hoje");
        }
    }

    public static void validarDataDevolucao(LocalDate dataDevolucao, LocalDate dataEmprestimo){
        if(dataDevolucao.isAfter(LocalDate.now())){
            throw new AtributoException("A data de devolução deve ser anterior à hoje");
        }

        if(dataEmprestimo.isAfter(dataDevolucao)){
            throw new AtributoException("A data de emprestimo não pode ser depois da data de devolução");
        }
    }

    public static void validarDataPrevistaDevolucao(LocalDate dataPrevista, LocalDate  dataEmprestimo){
        if (dataPrevista.isBefore(dataEmprestimo) || dataPrevista.isEqual(dataEmprestimo)) {
            throw new AtributoException("A data prevista de devolução deve ser posterior à data de empréstimo.");
        }
    }

    public Emprestimo salvarEmprestimo(Emprestimo emprestimo){
        validarEmprestimo(emprestimo);

        emprestimo.setSituacao(Situacao.PENDENTE);
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolverEmprestimo(Integer id, LocalDate dataDevolucao) {

        Emprestimo emprestimo = buscarEmprestimoPorId(id);

        validarDataDevolucao(
                dataDevolucao,
                emprestimo.getDataEmprestimo()
        );

        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setSituacao(Situacao.DEVOLVIDO);

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
        EmprestimoAtualizado.setSituacao(emprestimoDadosAtualizados.getSituacao());
        EmprestimoAtualizado.setDataPrevistaDevolucao(
                emprestimoDadosAtualizados.getDataPrevistaDevolucao()
        );

        return emprestimoRepository.save(EmprestimoAtualizado);
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

    public void validarEmprestimo(Emprestimo emprestimo) {

        if (emprestimo.getDataEmprestimo() == null) {
            emprestimo.setDataEmprestimo(LocalDate.now());
        }

        validarDataEmprestimo(emprestimo.getDataEmprestimo());

        validarDataPrevistaDevolucao(
                emprestimo.getDataPrevistaDevolucao(),
                emprestimo.getDataEmprestimo()
        );

        if (emprestimo.getDataDevolucao() != null) {
            validarDataDevolucao(
                    emprestimo.getDataDevolucao(),
                    emprestimo.getDataEmprestimo()
            );
        }
    }
}
