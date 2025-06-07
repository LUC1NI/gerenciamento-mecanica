package model;

import model.enums.StatusServico;
import model.enums.TipoCombustivel;
import model.enums.TipoServico;

public class Carro extends Veiculo{
    private static final long serialVersionUID = 1L;
    private String tracao;
    private boolean arCondicionado, automatico;
    private float motor;

    public Carro(String modelo, String marca, String cor, String placa, int ano, StatusServico status,
            TipoServico tipoServico, TipoCombustivel tipoCombustivel, Cliente cliente, Funcionario funcionario,
            String tracao, boolean arCondicionado, boolean automatico, float motor) {
        super(modelo, marca, cor, placa, ano, status, tipoServico, tipoCombustivel, cliente, funcionario);
        this.tracao = tracao;
        this.arCondicionado = arCondicionado;
        this.automatico = automatico;
        this.motor = motor;
    }

    @Override
    public float calcularCustoEstimadoServico() {
        float custoBase = 0.0f;
        if (getTipoServico() != null) {
            custoBase = getTipoServico().getPrecoBase();
        }
        if (isArCondicionado()) {
            custoBase += 30.00f;
        }
        if (isAutomatico()) {
            custoBase += 50.00f;
        }
        if (getMotor() > 2.0f) {
            custoBase += 75.00f;
        }
        return custoBase;
    }

    public String descricaoVeiculo(boolean detalhada) {
    if (detalhada) {
        return descricaoVeiculo();
    } else {
        return getMarca() + " " + getModelo() + " - " + getPlaca();
        }
    }

    public String getTracao() {return tracao;}
    public void setTracao(String tracao) {this.tracao = tracao;}
    public boolean isArCondicionado() {return arCondicionado;}
    public void setArCondicionado(boolean arCondicionado) {this.arCondicionado = arCondicionado;}
    public boolean isAutomatico() {return automatico;}
    public void setAutomatico(boolean automatico) {this.automatico = automatico;}
    public float getMotor() {return motor;}
    public void setMotor(float motor) {this.motor = motor;}

    @Override
    public String toString() {
    return "Carro {" +
            "tracao=" + tracao +
            ", arCondicionado=" + (arCondicionado ? "Sim" : "Não") +
            ", automatico=" + (automatico ? "Sim" : "Não") +
            ", motor=" + motor +
            ", combustivel=" + getTipoCombustivel() +
            '}';
    }

}
