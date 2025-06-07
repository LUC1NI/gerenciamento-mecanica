package controller;

import dal.FuncionarioDAO;
import exception.FuncionarioNaoEncontradoException;
import exception.ValorInvalidoException;
import factory.PessoaFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Funcionario;

public class FuncionarioController {
    private final List<Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = new ArrayList<>();
    }

    public void cadastrarFuncionario(String nome, String cpf, String telefone, String cargo, float salario) throws Exception {
        if (buscarFuncionarioPorCpf(cpf).isPresent()) {
            throw new ValorInvalidoException("Já existe um funcionário cadastrado com o CPF " + cpf);
        }
        Funcionario novoFuncionario = new PessoaFactory().criarFuncionario(nome, cpf, telefone, cargo, salario);
        funcionarios.add(novoFuncionario);
    }

    public Optional<Funcionario> buscarFuncionarioPorCpf(String cpf) {
        return funcionarios.stream()
                .filter(f -> f.getCpf().equals(cpf))
                .findFirst();
    }

    public void atualizarFuncionario(String cpf, Funcionario funcionarioAtualizado) throws FuncionarioNaoEncontradoException {
        Funcionario existente = buscarFuncionarioPorCpf(cpf)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário com CPF " + cpf + " não encontrado."));
        int index = funcionarios.indexOf(existente);
        funcionarios.set(index, funcionarioAtualizado);
    }

    public void removerFuncionario(String cpf) throws FuncionarioNaoEncontradoException {
        boolean removed = funcionarios.removeIf(f -> f.getCpf().equals(cpf));
        if (!removed) {
            throw new FuncionarioNaoEncontradoException("Funcionário com CPF " + cpf + " não encontrado.");
        }
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public void salvar() throws IOException {
        FuncionarioDAO.salvar(funcionarios);
    }

    public static List<Funcionario> carregar() throws IOException, ClassNotFoundException {
        return FuncionarioDAO.carregar();
    }
}
