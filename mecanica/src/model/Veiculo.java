package model;

import java.io.Serializable;
import model.enums.StatusServico;
import model.enums.TipoCombustivel;
import model.enums.TipoServico;

public abstract class Veiculo implements Serializable{  
    private static final long serialVersionUID = 1L;
    private String modelo, marca, cor, placa;
    private int ano;
    private StatusServico status;
    private TipoServico tipoServico;
    private TipoCombustivel tipoCombustivel;

    protected Veiculo(int ano, String cor, String marca, String modelo, String placa, StatusServico status, TipoCombustivel tipoCombustivel, TipoServico tipoServico) {
        this.ano = ano;
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.status = status;
        this.tipoCombustivel = tipoCombustivel;
        this.tipoServico = tipoServico;
    }

    public abstract float calcularCustoEstimadoServico();

    public String descricaoVeiculo() {
        return "Veiculo: " + modelo + " " + marca + " " + cor + " " + ano;
    }

    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}
    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}
    public String getCor() {return cor;}
    public void setCor(String cor) {this.cor = cor;}
    public int getAno() {return ano;}
    public void setAno(int ano) {this.ano = ano;}
    public StatusServico getStatus() {return status;}
    public void setStatus(StatusServico status) {this.status = status;}
    public TipoCombustivel getTipoCombustivel() {return tipoCombustivel;}
    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {this.tipoCombustivel = tipoCombustivel;}
    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}
    public TipoServico getTipoServico() {return tipoServico;}
    public void setTipoServico(TipoServico tipoServico) {this.tipoServico = tipoServico;}

    @Override
    public String toString() {
        return "Veiculo " + modelo + " " + marca + " " + cor + " placa " + placa + " " + ano
                + "status" + status + " tipoServico=" + tipoServico + " tipoCombustivel=" + tipoCombustivel;
    }
}
