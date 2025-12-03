package com.oficina.gerenciamento.enums;

public enum StatusServico {
    PENDENTE(1, "Pendente"),
    EM_ANDAMENTO(2, "Em andamento"),
    FINALIZADO(3, "Finalizado");

    private int id;
    private String nome;

    StatusServico(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {return id;}
    public String getNome() {return nome;}

    public static StatusServico obterStatusServicoPorId(int id) {
        for (StatusServico status : StatusServico.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("ID inválido");
    }

    @Override
    public String toString() {return nome;}
}