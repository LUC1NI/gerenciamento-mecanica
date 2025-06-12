package model;

import model.enums.StatusServico;
import model.enums.TipoCombustivel;
import model.enums.TipoServico;
import model.enums.TipoTracao;
import model.interfaces.IVeiculo;

public class Moto extends Veiculo implements IVeiculo{
    private static final long serialVersionUID = 1L;
    private int cilindradas, numMarchas;
    private boolean freioABS, carda, partidaEletrica;

    public Moto(String modelo, String marca, String cor, String placa, int ano, StatusServico status,
            TipoServico tipoServico, TipoCombustivel tipoCombustivel, TipoTracao tipoTracao, Cliente cliente,
            Funcionario funcionario, int cilindradas, int numMarchas, boolean freioABS, boolean carda,
            boolean partidaEletrica) {
        super(modelo, marca, cor, placa, ano, status, tipoServico, tipoCombustivel, tipoTracao, cliente, funcionario);
        this.cilindradas = cilindradas;
        this.numMarchas = numMarchas;
        this.freioABS = freioABS;
        this.carda = carda;
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public String verificarChecklistBasico() {
        return "Checklist da moto: Óleo OK, Freios OK, Freio ABS " + (freioABS ? "OK" : "Não disponível") + ".";
    }

    @Override
    public float calcularCustoEstimadoServico() {
        float custoBase = 0.0f;
        if (getTipoServico() != null) {
            custoBase = getTipoServico().getPrecoBase();
        }
        if (cilindradas > 500) {
            custoBase += 50.00f;
        }
        if (freioABS) { 
            custoBase += 25.00f;
        }
        if (carda) { 
            custoBase += 35.00f;
        }
        if (partidaEletrica) {
            custoBase += 10.00f;
        }
        return custoBase;
    }

    @Override
    public String descricaoVeiculo() {
        return "Moto: " + getMarca() + " " + getModelo() + ", Cilindradas: " + cilindradas + ", Marchas: " + numMarchas;
    }

    public String descricaoVeiculo(boolean detalhada) {
        if (detalhada) {
            return descricaoVeiculo(); 
        } 
            return getMarca() + " " + getModelo() + " (" + getPlaca() + ")";
    }

    public int getCilindradas() {return cilindradas;}
    public void setCilindradas(int cilindradas) {this.cilindradas = cilindradas;}
    public int getNumMarchas() {return numMarchas;}
    public void setNumMarchas(int numMarchas) {this.numMarchas = numMarchas;}
    public boolean isFreioABS() {return freioABS;}
    public void setFreioABS(boolean freioABS) {this.freioABS = freioABS;}
    public boolean isCarda() {return carda;}
    public void setCarda(boolean carda) {this.carda = carda;}
    public boolean isPartidaEletrica() {return partidaEletrica;}
    public void setPartidaEletrica(boolean partidaEletrica) {this.partidaEletrica = partidaEletrica;}
    
    @Override
    public String toString() {
        return "Moto " + cilindradas + " Marchas " + numMarchas + " freioABS" + freioABS +
               "partida Eletrica" + partidaEletrica;
    }
}
