package com.oficina.gerenciamento.entity;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; 

import com.oficina.gerenciamento.enums.TipoServico;

class MotoTest {

    @Test
    void deveCobrarTaxaExtraQuandoTiverPartidaEletrica() {
        Moto moto = new Moto();
        moto.setTipoServico(TipoServico.TROCA_OLEO); 
        moto.setPartidaEletrica(true); 

        BigDecimal custoFinal = moto.calcularCustoEstimadoServico();

        BigDecimal valorEsperado = new BigDecimal("140.00");
        
        Assertions.assertEquals(valorEsperado, custoFinal);
    }

    @Test
    void naoDeveCobrarTaxaSeNaoTiverPartidaEletrica() {
        Moto moto = new Moto();
        moto.setTipoServico(TipoServico.TROCA_OLEO); 
        moto.setPartidaEletrica(false); 

        BigDecimal custoFinal = moto.calcularCustoEstimadoServico();

        BigDecimal valorEsperado = new BigDecimal("120.00");
        
        Assertions.assertEquals(valorEsperado, custoFinal);
    }
}