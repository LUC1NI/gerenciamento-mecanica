package com.oficina.gerenciamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficina.gerenciamento.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
}
