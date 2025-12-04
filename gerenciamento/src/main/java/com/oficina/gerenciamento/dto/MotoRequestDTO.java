package com.oficina.gerenciamento.dto;

import com.oficina.gerenciamento.entity.Moto;
import com.oficina.gerenciamento.entity.Veiculo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MotoRequestDTO extends VeiculoRequestDTO {

    private int cilindradas;
    private int numMarchas;
    private boolean freioABS;
    private boolean carda;
    private boolean partidaEletrica;

    @Override
    public Veiculo toEntity() {
        Moto moto = new Moto();
        this.preencherDadosComuns(moto);
        
        moto.setCilindradas(this.cilindradas);
        moto.setNumMarchas(this.numMarchas);
        moto.setFreioABS(this.freioABS);
        moto.setCarda(this.carda);
        moto.setPartidaEletrica(this.partidaEletrica);
        return moto;
    }
}