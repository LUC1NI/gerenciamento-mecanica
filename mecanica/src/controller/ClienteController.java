package controller;

import dal.ClienteDAO;
import exception.ClienteNaoEncontradoException;
import exception.ValorInvalidoException;
import factory.PessoaFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Cliente;

public class ClienteController {
    private final List<Cliente> clientes;

    public ClienteController(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String endereco) throws Exception {
        if (buscarClientePorCpf(cpf).isPresent()) {
            throw new ValorInvalidoException("Já existe um cliente cadastrado com o CPF " + cpf);
        }
        Cliente novoCliente = new PessoaFactory().criarCliente(nome, cpf, telefone, endereco);
        clientes.add(novoCliente);
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    public void atualizarCliente(String cpf, Cliente clienteAtualizado) throws ClienteNaoEncontradoException {
        Cliente existente = buscarClientePorCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado."));
        int index = clientes.indexOf(existente);
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

    public void salvar() throws IOException {
        ClienteDAO.salvar(clientes);
    }

    public static List<Cliente> carregar() throws IOException, ClassNotFoundException {
        return ClienteDAO.carregar();
    }
}
