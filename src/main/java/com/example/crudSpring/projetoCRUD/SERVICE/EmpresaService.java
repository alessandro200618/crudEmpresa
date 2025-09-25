package com.example.crudSpring.projetoCRUD.SERVICE;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.crudSpring.projetoCRUD.ENTITY.Empresa;
import com.example.crudSpring.projetoCRUD.REPOSITORY.EmpresaRepository;

@Service
public class EmpresaService {
private final EmpresaRepository empresaRepository;

    // metodo construtor da classe EmpresaSeevice
    // criando uma ligação com a classe Repository
    
    public EmpresaService(EmpresaRepository ligacaoEmpresaRepository){
        empresaRepository = ligacaoEmpresaRepository;
    }
//selects ou inserts ou alter table ou delete
//select * From empresa (select realiza listagem de dados)
//retorna todos os dados da empresa (* - tudo da tabela)
public List<Empresa> findA11(){
return empresaRepository.findAll();
}//select * from empresa

public Empresa cadastarEmpresa(Empresa dadosEmpresa){
return empresaRepository.save(dadosEmpresa);
}

public void deletarEmpresa(Empresa dadosEmpresa){
    empresaRepository.delete(dadosEmpresa);
}
//realizar a busca de dados no banco usando  o id criado na classe

public Optional <Empresa> buscarPorId(Long id){
    return empresaRepository.findById(id);
}

public Empresa editarDadoEmpresa(Long id, Empresa dadosAtualizado){
   
    Empresa empresaBuscada = buscarPorId(id).orElseThrow(
    () -> new IllegalArgumentException("Empresa não encontrada"));
    
    empresaBuscada.setNome(dadosAtualizado.getNome());
    empresaBuscada.setCnpj(dadosAtualizado.getCnpj());
    empresaBuscada.setRamo(dadosAtualizado.getRamo());

    return empresaRepository.save(empresaBuscada);
}

public List<Empresa> buscarEmpresaPorNome(String nome_Empresa){
    return empresaRepository.findByNomeContainingIgnoreCase(nome_Empresa);
}
}
