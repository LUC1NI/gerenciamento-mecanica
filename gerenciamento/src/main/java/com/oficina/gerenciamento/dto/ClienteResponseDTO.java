package com.oficina.gerenciamento.dto;

import com.oficina.gerenciamento.entity.Cliente;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;

    public ClienteResponseDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.endereco = cliente.getEndereco();
    }
}