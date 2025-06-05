package factory;

import exception.ValorInvalidoException;
import java.util.List;
import model.Cliente;
import model.Funcionario;
import model.Veiculo;
import util.Validador;

public class PessoaFactory {
    public Funcionario criarFuncionario(String nome, String cpf, String telefone, String cargo, float salario,
            List<Veiculo> veiculosResponsaveis) throws ValorInvalidoException{
        Validador.validaNome(nome);
        Validador.validaCpf(cpf);
        Validador.validarTelefone(telefone);
        validaListaVeiculos(veiculosResponsaveis, nome);
        validaSalario(salario);
        return new Funcionario(nome, cpf, telefone, cargo, salario, veiculosResponsaveis);
    }

    public Cliente criarCliente(String nome, String cpf, String telefone, String endereco, List<Veiculo> veiculos) throws ValorInvalidoException{
        Validador.validaNome(nome);
        Validador.validaCpf(cpf);
        Validador.validarTelefone(telefone);
        Validador.validaStringNaoVazia(endereco, "Endereço");
        validaListaVeiculos(veiculos, nome);
        return new Cliente(nome, cpf, telefone, endereco, veiculos);
    }

    private static void validaSalario(float salario) throws ValorInvalidoException{
        if (salario < 1518.00f) {
            throw new ValorInvalidoException("Salário deve ser maior ou igual a R$ " + 1518.00f);
        }
    }

    private static void validaListaVeiculos(List<Veiculo> veiculos, String nome) throws ValorInvalidoException{
        if (veiculos == null || veiculos.isEmpty()) {
            throw new ValorInvalidoException(nome + " deve possuir ao menos um veículo.");
        }
    }
}
