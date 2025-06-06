package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;
    private String endereco;
    private List<Veiculo> veiculos = new ArrayList<>();

    public Cliente(String nome, String cpf, String telefone, String endereco, List<Veiculo> veiculos) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
        this.veiculos = veiculos;
    }
    
    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public boolean removerVeiculoPorPlaca(String placa) {
    return this.veiculos.removeIf(v -> v.getPlaca().equals(placa));
    }

    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public List<Veiculo> getVeiculos() {return veiculos;}
    public void setVeiculos(List<Veiculo> veiculos) {this.veiculos = veiculos;}

    @Override
    public String toString() {
        return "Cliente "+ getNome() + " " + endereco + "";
    }

}
