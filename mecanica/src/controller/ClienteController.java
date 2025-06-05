package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exception.ValorInvalidoException;
import factory.PessoaFactory;
import model.Cliente;

public class ClienteController {
    private final List<Cliente> clientes;

    public ClienteController() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String endereco, List<Veiculo> veiculos) {
        try {
            Cliente novoCliente = new PessoaFactory().criarCliente(nome, cpf, telefone, endereco, veiculos);
            clientes.add(novoCliente);
        } catch (ValorInvalidoException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao cadastrar cliente.");
        }
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return clientes.stream()
            .filter(c -> c.getCpf().equals(cpf))
            .findFirst();
    }

    public boolean atualizarCliente(String cpf, Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getCpf().equals(cpf)) {
                clientes.set(i, clienteAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean removerCliente(String cpf) {
        return clientes.removeIf(c -> c.getCpf().equals(cpf));
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

}

