package com.oficina.gerenciamento.enums;

import java.math.BigDecimal;

public enum TipoServico {
    TROCA_OLEO("Troca de Óleo", new BigDecimal("120.00")),
    REVISAO_GERAL("Revisão Geral", new BigDecimal("550.00")),
    ALINHAMENTO_BALANCEAMENTO("Alinhamento e Balanceamento", new BigDecimal("150.00")),
    FREIOS("Manutenção de Freios", new BigDecimal("300.00")),
    SUSPENSAO("Manutenção de Suspensão", new BigDecimal("400.00")),
    ELETRICA("Diagnóstico Elétrico", new BigDecimal("100.00")),
    FUNILARIA_PINTURA("Funilaria e Pintura", new BigDecimal("800.00"));

    private final String descricao;
    private final BigDecimal precoBase;

    TipoServico(String descricao, BigDecimal precoBase) {
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    public String getDescricao() {return descricao;}
    public BigDecimal getPrecoBase() {return precoBase;}

    public static TipoServico obterPorDescricao(String descricao) {
        for (TipoServico tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Serviço não encontrado: " + descricao);
    }

    @Override
    public String toString() {return descricao + " (R$ " + precoBase + ")";}
}