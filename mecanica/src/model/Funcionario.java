package model;

import java.util.List;

public class Funcionario extends Pessoa{
    private static final long serialVersionUID = 1L;
    private String cargo;
    private float salario;
    private List<Veiculo> veiculosResponsaveis;

    public Funcionario(String nome, String cpf, String telefone, String cargo, float salario,
            List<Veiculo> veiculosResponsaveis) {
        super(nome, cpf, telefone);
        this.cargo = cargo;
        this.salario = salario;
        this.veiculosResponsaveis = veiculosResponsaveis;
    }
    
    public String getCargo() {return cargo;}
    public void setCargo(String cargo) {this.cargo = cargo;}
    public float getSalario() {return salario;}
    public void setSalario(float salario) {this.salario = salario;}
    public List<Veiculo> getVeiculosResponsaveis() {return veiculosResponsaveis;}
    public void setVeiculosResponsaveis(List<Veiculo> veiculos) { this.veiculosResponsaveis = veiculos; }

    @Override
    public String toString() {
        return "Funcionario [cargo=" + cargo + ", salario=" + salario + "]";
    }
}
