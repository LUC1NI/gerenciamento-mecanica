package model.enums;

public enum TipoCombustivel {
    FLEX(1, "Flex"),
    GASOLINA(2, "Gasolina"),
    DIESEL(3, "Diesel"),
    ETANOL(4, "Etanol"),
    ELETRICO(5, "Elétrico"),;

    private int id;
    private String nome;

    private TipoCombustivel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {return id;}
    public String getNome() {return nome;}

    public static TipoCombustivel getIdTipoCombustivel(int id) {
        for (TipoCombustivel tipo : TipoCombustivel.values()){
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id não encontrado");
    }

    @Override
    public String toString() {
        return nome;
    }

}
