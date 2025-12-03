package com.oficina.gerenciamento.repository;

import com.oficina.gerenciamento.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    Optional<Veiculo> findByPlaca(String placa);
}