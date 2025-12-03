package com.oficina.gerenciamento.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo{
    
    private boolean arCondicionado, automatico;
    private double motor;

    @Override
    public BigDecimal calcularCustoEstimadoServico() {
        BigDecimal preco = this.getTipoServico().getPrecoBase();

        if (automatico) {
            preco = preco.add(new BigDecimal("50"));
        }

        return preco;
    }

}
