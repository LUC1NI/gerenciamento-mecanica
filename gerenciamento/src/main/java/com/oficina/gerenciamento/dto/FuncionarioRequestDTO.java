package com.oficina.gerenciamento.dto;

import java.math.BigDecimal;

import com.oficina.gerenciamento.entity.Funcionario;

import lombok.Data;

@Data
public class FuncionarioRequestDTO {
    
    private String nome;
    private String cpf;
    private String telefone;
    private String cargo;
    private BigDecimal salario; 

    public Funcionario toEntity() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(this.nome);
        funcionario.setCpf(this.cpf);
        funcionario.setTelefone(this.telefone);
        funcionario.setCargo(this.cargo);
        funcionario.setSalario(this.salario);
        return funcionario;
    }
}