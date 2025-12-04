package com.oficina.gerenciamento.dto;

import com.oficina.gerenciamento.entity.Carro;
import com.oficina.gerenciamento.entity.Veiculo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarroRequestDTO extends VeiculoRequestDTO {
    
    private boolean arCondicionado;
    private boolean automatico;
    private double motor;

    @Override
    public Veiculo toEntity() {
        Carro carro = new Carro();
        this.preencherDadosComuns(carro);
        
        carro.setArCondicionado(this.arCondicionado);
        carro.setAutomatico(this.automatico);
        carro.setMotor(this.motor);
        return carro;
    }
}