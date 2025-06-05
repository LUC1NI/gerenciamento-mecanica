package model.enums;

public enum StatusServico {
    PENDENTE (1, "Pendente"),
    EM_ANDAMENTO (2, "Em andamento"),
    FINALIZADO(3, "Finalizado");

    private int id;
    private String nome;

    private StatusServico(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {return id;}
    public String getNome() {return nome;}

    public static StatusServico ObterstatusServicoPorId(int id) {
        for (StatusServico status : StatusServico.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("ID inválido");
    }

    @Override
    public String toString() {
        return nome;
    }
}
