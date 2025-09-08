package com.example.crudSpring.projetoCRUD.ENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_empresa")
@NoArgsConstructor
@Getter
@Setter
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", nullable = false, unique=true)
    private Long id_empresa;

    @Column(name = "nome_empresa", nullable = false, length=100)
    private String nome_empresa;

    @Column(name = "cnpj", nullable = false, length=20, unique=true)
    private int cnpj;

    @Column(name = "ramo", nullable = false, length=100)
    private String ramo;

    public Empresa(String nome_empresa, int cnpj, String ramo) {
        this.nome_empresa = nome_empresa;
        this.cnpj = cnpj;
        this.ramo = ramo;
    }
}
