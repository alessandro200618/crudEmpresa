package com.example.crudSpring.projetoCRUD.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;
import com.example.crudSpring.projetoCRUD.SERVICE.EmpresaService;
import com.example.crudSpring.projetoCRUD.SERVICE.FuncionarioService;

@Controller
@RequestMapping("/funcionarioCTR")
public class FuncionarioController {

    @Autowired
    private FuncionarioService ligacaoFuncionarioService;

    @Autowired
    private EmpresaService ligacaoEmpresaService;

    @GetMapping("/listarFunc")
    public String listarTodosFuncionario(Model oModel) {
        oModel.addAttribute("funcionarios", ligacaoFuncionarioService.listarTodosFuncionarios());
        oModel.addAttribute("empresas", ligacaoEmpresaService.findAll());

        return "listarFuncionarios";
    }

    @GetMapping("/formFuncionario")
    public String mostrarFormCadastro(Model oModel) {

        oModel.addAttribute("funcionario", new Funcionario());
        oModel.addAttribute("empresas", ligacaoEmpresaService.findAll());

        return "cadastrarFuncionario";
    }

    @PostMapping("/salvarFuncionario")
    public String cadastrarFuncionario(@ModelAttribute Funcionario objFuncionario) {
        ligacaoFuncionarioService.cadastrarFuncionario(objFuncionario);
        return "redirect:/funcionarioCTR/listarFunc";
    }

    @GetMapping("/deletarFuncionario/{id}")
    public String excluirFuncionario(@PathVariable("id") Long id) {
        ligacaoFuncionarioService.excluirFuncionario(id);
        return "redirect:/funcionarioCTR/listarFunc";
    }

    @GetMapping("/formAtualizar/{id}")
    public String formAtualizarFuncionario(@PathVariable("id") Long id, Model oModel) {

        Funcionario funcionarioEncontrado = ligacaoFuncionarioService.buscarFuncionarioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado"));

        oModel.addAttribute("funcionario", funcionarioEncontrado);
        oModel.addAttribute("empresas", ligacaoEmpresaService.findAll());

        return "editarFuncionario";
    }

    @PostMapping("/atualizarFuncionario/{id}")
    public String editarFuncionario(@PathVariable("id") Long id,
                                    @ModelAttribute Funcionario objFuncionarioAtualizado) {

        ligacaoFuncionarioService.atualizarFuncionario(id, objFuncionarioAtualizado);

        return "redirect:/funcionarioCTR/listarFunc";
    }

    @GetMapping("/buscarFuncionarioPorNome")
    public String buscarFuncionarioPorNome(
            @RequestParam(value = "nome", required = false) String nome,
            Model model) {

        if (nome != null && !nome.isBlank()) {
            model.addAttribute("funcionarioNome",
                    ligacaoFuncionarioService.buscarFuncionarioPorNome(nome));
        } else {
            model.addAttribute("funcionarioNome", null);
        }

        model.addAttribute("paramNome", nome);

        return "buscarFuncNome";
    }
}
