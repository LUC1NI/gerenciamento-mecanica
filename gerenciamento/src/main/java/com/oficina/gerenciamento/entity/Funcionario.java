package com.oficina.gerenciamento.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Funcionario extends Pessoa{
    private String cargo;
    private BigDecimal salario;
}
