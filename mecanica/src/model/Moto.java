package model;

import model.enums.StatusServico;
import model.enums.TipoCombustivel;
import model.enums.TipoServico;

public class Moto extends Veiculo{
    private static final long serialVersionUID = 1L;
    private int cilindradas, numMarchas;
    private boolean freioABS, carda, partidaEletrica;

    public Moto(boolean carda, int cilindradas, boolean freioABS, int numMarchas, boolean partidaEletrica, int ano, String cor, String marca, String modelo, String placa, StatusServico status, TipoCombustivel tipoCombustivel, TipoServico tipoServico) {
        super(ano, cor, marca, modelo, placa, status, tipoCombustivel, tipoServico);
        this.carda = carda;
        this.cilindradas = cilindradas;
        this.freioABS = freioABS;
        this.numMarchas = numMarchas;
        this.partidaEletrica = partidaEletrica;
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
        } else {
            return getMarca() + " " + getModelo() + " (" + getPlaca() + ")";
        }
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
