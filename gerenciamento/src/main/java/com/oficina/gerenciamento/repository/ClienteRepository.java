package com.oficina.gerenciamento.repository;

import com.oficina.gerenciamento.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}