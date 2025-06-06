package controller;

import dal.FuncionarioDAO;
import exception.FuncionarioNaoEncontradoException;
import exception.ValorInvalidoException;
import exception.VeiculoNaoEncontradoException;
import factory.PessoaFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Funcionario;
import model.Veiculo;

public class FuncionarioController {
    private final List<Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = new ArrayList<>();
    }

    public void cadastrarFuncionario(String nome, String cpf, String telefone, String cargo, float salario, List<Veiculo> veiculos)
            throws Exception {
        if (buscarFuncionarioPorCpf(cpf).isPresent()) {
            throw new ValorInvalidoException("Já existe um funcionário cadastrado com o CPF " + cpf);
        }
        Funcionario novoFuncionario = new PessoaFactory().criarFuncionario(nome, cpf, telefone, cargo, salario, veiculos);
        funcionarios.add(novoFuncionario);
    }

    public Optional<Funcionario> buscarFuncionarioPorCpf(String cpf) {
        return funcionarios.stream()
                .filter(f -> f.getCpf().equals(cpf))
                .findFirst();
    }

    public void atualizarFuncionario(String cpf, Funcionario funcionarioAtualizado)
            throws FuncionarioNaoEncontradoException {
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

    public void adicionarVeiculoResponsavel(String cpf, Veiculo veiculo)
            throws FuncionarioNaoEncontradoException {
        Funcionario funcionario = buscarFuncionarioPorCpf(cpf)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário com CPF " + cpf + " não encontrado."));
        funcionario.getVeiculosResponsaveis().add(veiculo);
    }

    public void removerVeiculoResponsavelPorPlaca(String cpf, String placa) throws FuncionarioNaoEncontradoException, VeiculoNaoEncontradoException {
        Optional<Funcionario> funcionarioOpt = buscarFuncionarioPorCpf(cpf);
        if (funcionarioOpt.isPresent()) {
            Funcionario funcionario = funcionarioOpt.get();
            boolean removed = funcionario.getVeiculosResponsaveis().removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
            if (!removed) {
                throw new VeiculoNaoEncontradoException("Veículo com placa " + placa + " não encontrado na lista do funcionário.");
            }
        } else {
            throw new FuncionarioNaoEncontradoException("Funcionário com CPF " + cpf + " não encontrado.");
        }
    }

    public List<Veiculo> listarVeiculosResponsaveis(String cpf) throws FuncionarioNaoEncontradoException {
        Funcionario funcionario = buscarFuncionarioPorCpf(cpf)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário com CPF " + cpf + " não encontrado."));
        return funcionario.getVeiculosResponsaveis();
    }

    public void salvar() throws IOException {
        FuncionarioDAO.salvar(funcionarios);
    }

    public static List<Funcionario> carregar() throws IOException, ClassNotFoundException {
        return FuncionarioDAO.carregar();
    }
}
