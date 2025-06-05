package model.enums;

public enum TipoServico {
    TROCA_OLEO("Troca de Óleo", 120.00f),
    REVISAO_GERAL("Revisão Geral", 550.00f),
    ALINHAMENTO_BALANCEAMENTO("Alinhamento e Balanceamento", 150.00f),
    FREIOS("Manutenção de Freios", 300.00f),
    SUSPENSAO("Manutenção de Suspensão", 400.00f),
    ELETRICA("Diagnóstico Elétrico", 100.00f),
    FUNILARIA_PINTURA("Funilaria e Pintura", 800.00f);

    private final String descricao; 
    private final float precoBase;   

    private TipoServico(String descricao, float precoBase) {
        this.descricao = descricao;
        this.precoBase = precoBase;
    }

    public static TipoServico getByDescricao(String descricao) {
        for (TipoServico tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        return null;
    }

    public String getDescricao() {return descricao;}
    public float getPrecoBase() {return precoBase;}

    @Override
    public String toString() {
        return descricao + " (R$ " + String.format("%.2f", precoBase) + ")";
    }

}