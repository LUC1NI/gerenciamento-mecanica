package model.enums;

public enum TipoTracao {
    QUATROXQUATRO(1,"4X4"),
    QUATROXDOIS(2,"4X2"),
    DOISXUM(3,"2X1");

    private final int id;
    private final String descricao;

    private TipoTracao(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {return id;}
    public String getDescricao() {return descricao;}

    public static TipoTracao getIdTipoTracao(int id){
        for (TipoTracao tipo : TipoTracao.values()){
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id não encontrado");
    }

    @Override
    public String toString(){
        return descricao;
    }
}
