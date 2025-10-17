package com.example.crudSpring.projetoCRUD.CONTROLLER;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.crudSpring.projetoCRUD.ENTITY.Empresa;
import com.example.crudSpring.projetoCRUD.SERVICE.EmpresaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/empresaCTR")

public class EmpresaController {
    private final EmpresaService empresaService;
    public EmpresaController(EmpresaService ligacaoEmpresaService){
        this.empresaService = ligacaoEmpresaService;
    }

//chamada para o listar todas as empresas

    @GetMapping("/listarTodasEmpresas")
    public String listarEmpresas(Model oModel){
        oModel.addAttribute("empresas", empresaService.findAll());
        return "listarEmpresas";
    }

    @GetMapping("/viewCadEmpresa")
    public String mostrarFormCadastro(Model oModel){
        oModel.addAttribute("empresa", new Empresa());
        return "cadastroEmpresa";
    }

@PostMapping("/salvarEmpresa")
public String salvarEmpresa(@ModelAttribute Empresa objempresa) {
    //chamando o metodo cadastrar e passando o objeto ("pacotinho") com os dados que precisam ser salvos 
    empresaService.cadastrarEmpresa(objempresa);
    return "redirect:/empresaCTR/listarTodasEmpresas";
}

@GetMapping("/editar/{id}")
public String formEditar(@PathVariable("id") Long id, Model oModel) {
    Empresa objEmpresa = empresaService.buscaPorId(id)
        .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
 
    oModel.addAttribute("empresaEditar", objEmpresa);
    return "editarEmpresa";
}

//PathVarieble está pegando o id que vem do BDD

@PostMapping("/atualizarEmpresa/{id}")
public String atualizarEmpresa(@PathVariable ("id") Long id, @ModelAttribute Empresa objEmpresaAtualizada) {

    empresaService.editarDadoEmpresa(id, objEmpresaAtualizada);
    //TODO: process POST request
    
    return "redirect:/empresaCTR/listarTodasEmpresas";
}
@GetMapping("/deletarEmpresa/{id}")
public String apagarEmpresa(@PathVariable ("id") Long id) {

empresaService.deletarEmpresa(id);

    return "redirect:/empresaCTR/listarTodasEmpresas";
}
@GetMapping("/buscarEmpresaPorNome")
public List<Empresa> executarPorNome(@RequestParam ("nome") String nome_empresa, Model oModel) {
    return empresaService.buscarEmpresaPorNome(nome_empresa);
}


}          