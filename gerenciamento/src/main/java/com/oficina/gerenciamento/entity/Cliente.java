package com.oficina.gerenciamento.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Cliente extends Pessoa{
    private String endereco;
}
