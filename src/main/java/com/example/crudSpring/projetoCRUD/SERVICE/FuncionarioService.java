package com.example.crudSpring.projetoCRUD.SERVICE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;
import com.example.crudSpring.projetoCRUD.REPOSITORY.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository ligacaoFuncionarioRepository;

    public List<Funcionario> listarTodosFuncionarios() {
        return ligacaoFuncionarioRepository.findAll();
    }

    public Funcionario cadastrarFuncionario(Funcionario dadosFuncionario) {
        return ligacaoFuncionarioRepository.save(dadosFuncionario);
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return ligacaoFuncionarioRepository.findById(id);
    }

    public void deletarFuncionario(Long id) {
        ligacaoFuncionarioRepository.deleteById(id);
    }

    @Transactional
    public void atualizarFuncionario(Long id, Funcionario dadosAtualizados) {
        Funcionario objFuncionario = buscarFuncionarioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado")); 
        objFuncionario.setNome(dadosAtualizados.getNome());
        objFuncionario.setSalario(dadosAtualizados.getSalario());
        objFuncionario.setCargo(dadosAtualizados.getCargo());
        objFuncionario.setIdentificadorEmpresa(dadosAtualizados.getIdentificadorEmpresa());
    }

    // Método correto de exclusão usado pelo Controller
    public void excluirFuncionario(Long id) {
        ligacaoFuncionarioRepository.deleteById(id);
    }
    public List<Funcionario> buscarFuncionarioPorNome(String nome) {
    return ligacaoFuncionarioRepository.findByNomeContainingIgnoreCase(nome);
}

}
