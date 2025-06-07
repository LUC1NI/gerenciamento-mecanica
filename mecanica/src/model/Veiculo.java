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
    private Cliente cliente;
    private Funcionario funcionario;

    protected Veiculo(String modelo, String marca, String cor, String placa, int ano, StatusServico status,
            TipoServico tipoServico, TipoCombustivel tipoCombustivel, Cliente cliente, Funcionario funcionario) {
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
        this.placa = placa;
        this.ano = ano;
        this.status = status;
        this.tipoServico = tipoServico;
        this.tipoCombustivel = tipoCombustivel;
        this.cliente = cliente;
        this.funcionario = funcionario;
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
    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}
    public Funcionario getFuncionario() {return funcionario;}
    public void setFuncionario(Funcionario funcionario) {this.funcionario = funcionario;}

    @Override
    public String toString() {
        return "Veiculo " + modelo + " " + marca + " " + cor + " placa " + placa + " " + ano
                + "status" + status + " tipoServico=" + tipoServico + " tipoCombustivel=" + tipoCombustivel;
    }
}
