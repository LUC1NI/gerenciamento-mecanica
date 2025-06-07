package model;

public class Funcionario extends Pessoa{
    private static final long serialVersionUID = 1L;
    private String cargo;
    private float salario;

    public Funcionario(String nome, String cpf, String telefone, String cargo, float salario) {
        super(nome, cpf, telefone);
        this.cargo = cargo;
        this.salario = salario;
    }
    
    public String getCargo() {return cargo;}
    public void setCargo(String cargo) {this.cargo = cargo;}
    public float getSalario() {return salario;}
    public void setSalario(float salario) {this.salario = salario;}

    @Override
    public String toString() {
        return "Funcionario [cargo=" + cargo + ", salario=" + salario + "]";
    }
}
