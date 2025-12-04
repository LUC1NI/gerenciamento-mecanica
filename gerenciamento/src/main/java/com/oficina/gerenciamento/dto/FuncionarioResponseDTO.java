package com.oficina.gerenciamento.dto;

import com.oficina.gerenciamento.entity.Funcionario;

import lombok.Data;

@Data
public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private String cargo;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
    }
}