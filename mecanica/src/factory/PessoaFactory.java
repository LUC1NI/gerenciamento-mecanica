package factory;

import exception.ValorInvalidoException;
import model.Cliente;
import model.Funcionario;
import util.Validador;

public class PessoaFactory {
    public Funcionario criarFuncionario(String nome, String cpf, String telefone, String cargo, float salario) throws Exception{
        Validador.validaNome(nome);
        Validador.validaCpf(cpf);
        Validador.validarTelefone(telefone);
        validaSalario(salario);
        return new Funcionario(nome, cpf, telefone, cargo, salario);
    }

    public Cliente criarCliente(String nome, String cpf, String telefone, String endereco) throws Exception{
        Validador.validaNome(nome);
        Validador.validaCpf(cpf);
        Validador.validarTelefone(telefone);
        Validador.validaStringNaoVazia(endereco, "Endereço");
        return new Cliente(nome, cpf, telefone, endereco);
    }

    private static void validaSalario(float salario) throws ValorInvalidoException{
        if (salario < 1518.00f) {
            throw new ValorInvalidoException("Salário deve ser maior ou igual a R$ " + 1518.00f);
        }
    }
}
