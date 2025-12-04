package com.oficina.gerenciamento.dto;

import java.math.BigDecimal;

import com.oficina.gerenciamento.entity.Funcionario; 

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FuncionarioRequestDTO {
    
    @NotBlank(message = "O nome é obrigatório") 
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00")
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
    
    @NotBlank(message = "O cargo é obrigatório")
    private String cargo;

    @NotNull(message = "O salário é obrigatório")
    @Positive(message = "O salário deve ser maior que zero") 
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