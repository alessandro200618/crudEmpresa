package com.example.crudSpring.projetoCRUD.SERVICE;
import java.util.List;
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

}
