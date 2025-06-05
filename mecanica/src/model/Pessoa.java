package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nome, cpf, telefone;

    protected Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    @Override
    public String toString() {
        return "nome" + nome + ", cpf" + cpf + ", telefone" + telefone + "";
    } 
}
