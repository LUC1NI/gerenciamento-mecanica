package model;

public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;
    private String endereco;

    public Cliente(String nome, String cpf, String telefone, String endereco) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
    }
    
    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {this.endereco = endereco;}

    @Override
    public String toString() {
        return "Cliente "+ getNome() + " " + endereco + "";
    }

}
