package com.oficina.gerenciamento.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("MOTO")
@Entity
public class Moto extends Veiculo{
    private int cilindradas, numMarchas;
    private boolean freioABS, carda, partidaEletrica;

    @Override
    public BigDecimal calcularCustoEstimadoServico() {
        BigDecimal preco = this.getTipoServico().getPrecoBase();

        if (partidaEletrica) {
            preco = preco.add(new BigDecimal("20.00"));
        }

        return preco;
    }
}
