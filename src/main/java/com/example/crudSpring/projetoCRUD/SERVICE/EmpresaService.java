package com.example.crudSpring.projetoCRUD.SERVICE;
import java.util.List;
import java.util.Optional;

import org.springframework.data.relational.core.conversion.SaveBatchingAggregateChange;
import org.springframework.stereotype.Service;
import com.example.crudSpring.projetoCRUD.ENTITY.Empresa;
import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;
import com.example.crudSpring.projetoCRUD.REPOSITORY.EmpresaRepository;
@Service
public class EmpresaService {
 
 private final EmpresaRepository empresaRepository;
    //4 operações
    //Selects, Inserts, Alter Table ou Delete

    //retorna todos os dados da empresa
    //(*) = Tudo da tabela
    //SELECT * FROM EMPRESA  (Select realiza listagem de dados)
    //Select (Quais dados deseja listar) ([From]onde executar a tabela) 

    // método construtor da classe EmpresaSeevice criando uma ligação com a classe Repository
    public EmpresaService(EmpresaRepository ligaçãoEmpresaRepository){
        empresaRepository = ligaçãoEmpresaRepository;
    }

       public List<Empresa> findAll(){
        return empresaRepository.findAll();
       }

       public Empresa cadastrarEmpresa(Empresa dadosEmpresa){
        return empresaRepository.save(dadosEmpresa); 
       }

       public void deletarEmpresa(Long id ){
        empresaRepository.deleteById(id);
       }

       //realiza a busca de dados usando o id criado na classe 
       public Optional<Empresa> buscaPorId(Long id){
        return empresaRepository.findById(id);
       }

//get seria pegar esses dados do BDD;
//set seria "setar" os dados do BDD
       public Empresa editarDadoEmpresa(Long id, Empresa dadosAtualizados){
        Empresa empresaBuscada = buscaPorId(id).orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
        empresaBuscada.setNome(dadosAtualizados.getNome());
        empresaBuscada.setRamo(dadosAtualizados.getRamo());
        empresaBuscada.setCnpj(dadosAtualizados.getCnpj());
        return empresaRepository.save(empresaBuscada);
    }
    public List<Empresa> buscarEmpresaPorNome(String nome_empresa){
        return empresaRepository.findByNomeContainingIgnoreCase(nome_empresa);
    }

    public void atualizarFuncionario(Long id, Funcionario objFuncionarioAtualizado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarFuncionario'");
    }
}