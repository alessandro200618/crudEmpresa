package com.example.crudSpring.projetoCRUD.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crudSpring.projetoCRUD.ENTITY.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    
}
