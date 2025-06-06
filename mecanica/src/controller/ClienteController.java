package controller;

import dal.ClienteDAO;
import exception.ClienteNaoEncontradoException;
import exception.ValorInvalidoException;
import exception.VeiculoNaoEncontradoException;
import factory.PessoaFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Cliente;
import model.Veiculo;

public class ClienteController {
    private final List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String endereco, List<Veiculo> veiculos) throws Exception {
        if (buscarClientePorCpf(cpf).isPresent()) {
        throw new ValorInvalidoException("Já existe um cliente cadastrado com o CPF " + cpf);
        }
        Cliente novoCliente = new PessoaFactory().criarCliente(nome, cpf, telefone, endereco, veiculos);
        clientes.add(novoCliente);
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return clientes.stream()
            .filter(c -> c.getCpf().equals(cpf))
            .findFirst();
    }

    public void atualizarCliente(String cpf, Cliente clienteAtualizado) throws ClienteNaoEncontradoException {
        Cliente clienteExistente = buscarClientePorCpf(cpf)
            .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado."));
        int index = clientes.indexOf(clienteExistente);
        clientes.set(index, clienteAtualizado);
    }

    public void removerCliente(String cpf) throws ClienteNaoEncontradoException {
        boolean removed = clientes.removeIf(c -> c.getCpf().equals(cpf));
     if (!removed) {
        throw new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public void adicionarVeiculoAoCliente(String cpf, Veiculo veiculo) throws ClienteNaoEncontradoException {
        Optional<Cliente> clienteOpt = buscarClientePorCpf(cpf);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
        cliente.adicionarVeiculo(veiculo);
        } else {
        throw new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public void removerVeiculoDoClientePorPlaca(String cpf, String placa) throws ClienteNaoEncontradoException, VeiculoNaoEncontradoException {
        Optional<Cliente> clienteOpt = buscarClientePorCpf(cpf);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            boolean removed = cliente.removerVeiculoPorPlaca(placa);
            if (!removed) {
                throw new VeiculoNaoEncontradoException("Veículo com placa " + placa + " não encontrado na lista do cliente.");
            }
        } else {
            throw new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public List<Veiculo> listarVeiculosDoCliente(String cpf) throws ClienteNaoEncontradoException {
        Cliente cliente = buscarClientePorCpf(cpf)
            .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado."));
        return cliente.getVeiculos();
    }

    public void salvar() throws IOException {
        ClienteDAO.salvar(clientes);
    }

    public static List<Cliente> carregar() throws IOException, ClassNotFoundException{
        return ClienteDAO.carregar();
    }
}