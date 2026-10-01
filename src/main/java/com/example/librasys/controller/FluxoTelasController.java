package com.example.librasys.controller;

import com.example.librasys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FluxoTelasController {


    @Autowired
    private LivroService livroService;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private ExemplarService exemplarService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        model.addAttribute("totalLivros",
                livroService.contarLivros());

        model.addAttribute("totalExemplares",
                exemplarService.contarExemplares());

        model.addAttribute("emprestimosAtivos",
                emprestimoService.contarAtivos());

        model.addAttribute("emprestimosAtrasados",
                emprestimoService.contarAtrasados());

        model.addAttribute("emprestimosRecentes",
                emprestimoService.listarRecentes());

        model.addAttribute("exemplaresDisponiveis",
                exemplarService.contarDisponiveis());

        model.addAttribute("exemplaresEmprestados",
                exemplarService.contarEmprestados());

        model.addAttribute("exemplaresManutencao",
                exemplarService.contarEmManutencao());

        return "dashboard";
    }

    @GetMapping("/livros")
    public String livros(Model model){

        model.addAttribute("livros", livroService.buscarTodosLivros());
        model.addAttribute("editoras", editoraService.BuscarTodasEditoras());
        model.addAttribute("autores", autorService.buscarTodosAutores());

        return "livros";
    }

    @GetMapping("/exemplares")
    public String exemplares(Model model){

        model.addAttribute("exemplares", exemplarService.buscarTodosExemplares());
        model.addAttribute("livros", livroService.buscarTodosLivros());

        return "exemplares";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model){

        model.addAttribute("usuarios", usuarioService.listarUsuario());

        return "usuarios";
    }

    @GetMapping("/autores")
    public String autores(Model model){

        model.addAttribute("autores", autorService.buscarTodosAutores());

        return "autores";
    }

    @GetMapping("/editoras")
    public String editoras(Model model){

        model.addAttribute("editoras", editoraService.BuscarTodasEditoras());

        return "editoras";
    }

    @GetMapping("/emprestimos")
    public String emprestimos(Model model){

        model.addAttribute("emprestimos", emprestimoService.buscarTodosEmprestimos());
        model.addAttribute("usuarios", usuarioService.listarAtivos());
        model.addAttribute("exemplaresDisponiveis",
                exemplarService.buscarExemplaresDisponiveis());


        return "emprestimos";
    }
}
