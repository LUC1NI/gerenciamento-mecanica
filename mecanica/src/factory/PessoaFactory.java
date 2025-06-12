package factory;

import exception.StringVaziaException;
import exception.ValorInvalidoException;
import model.Cliente;
import model.Funcionario;
import util.Validador;

public class PessoaFactory {
    public Funcionario criarFuncionario(String nome, String cpf, String telefone, String cargo, float salario) throws Exception{
        validaNome(nome);
        validaCpf(cpf);
        validarTelefone(telefone);
        validaSalario(salario);
        return new Funcionario(nome, cpf, telefone, cargo, salario);
    }

    public Cliente criarCliente(String nome, String cpf, String telefone, String endereco) throws Exception{
        validaNome(nome);
        validaCpf(cpf);
        validarTelefone(telefone);
        Validador.validaStringNaoVazia(endereco, "Endereço");
        return new Cliente(nome, cpf, telefone, endereco);
    }

    private static void validaSalario(float salario) throws ValorInvalidoException{
        if (salario < 1518.00f) {
            throw new ValorInvalidoException("Salário deve ser maior ou igual a R$ " + 1518.00f);
        }
    }

    private static void validaCpf(String cpf) throws ValorInvalidoException, StringVaziaException{
        Validador.validaStringNaoVazia(cpf, "CPF");
        if (!cpf.matches("\\d{11}")) {
            throw new ValorInvalidoException("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }
    }

    private static void validaNome(String nome) throws ValorInvalidoException, StringVaziaException{
        Validador.validaStringNaoVazia(nome, "nome");
        if (nome.length() < 3 ) {
            throw new ValorInvalidoException("Nome deve ter mais de 3 caracteres.");
        }
    }

    public static void validarTelefone(String telefone) throws ValorInvalidoException, StringVaziaException{
        Validador.validaStringNaoVazia(telefone, "Telefone");
        if (!telefone.matches("\\d{10,11}")) {
            throw new ValorInvalidoException("Telefone inválido. Deve conter 10 ou 11 dígitos numéricos.");
        }
    }
}
