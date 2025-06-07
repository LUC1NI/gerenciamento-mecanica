package views;

import controller.ClienteController;
import exception.ClienteNaoEncontradoException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import model.Cliente;

public class ClienteView {
    private final ClienteController clienteController;
    private final Scanner scanner;

    public ClienteView(ClienteController clienteController) {
        this.clienteController = clienteController;
        this.scanner = new Scanner(System.in);
    }

    public void menuCliente() {
        while (true) {
            System.out.println("\n=== MENU CLIENTE ===");
            System.out.println("1. Listar clientes");
            System.out.println("2. Cadastrar cliente");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Remover cliente");
            System.out.println("5. Buscar cliente por CPF");
            System.out.println("6. Salvar clientes");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1":
                        listarClientes();
                        break;
                    case "2":
                        cadastrarCliente();
                        break;
                    case "3":
                        atualizarCliente();
                        break;
                    case "4":
                        removerCliente();
                        break;
                    case "5":
                        buscarClientePorCpf();
                        break;
                    case "6":
                        salvarClientes();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\nLista de clientes:");
            for (Cliente c : clientes) {
                System.out.println("- Nome: " + c.getNome() + ", CPF: " + c.getCpf() + ", Telefone: "
                        + c.getTelefone() + ", Endereço: " + c.getEndereco());
            }
        }
    }

    private void cadastrarCliente() throws Exception {
        System.out.println("\nCadastrar novo cliente:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF (somente números): ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        clienteController.cadastrarCliente(nome, cpf, telefone, endereco);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void atualizarCliente() throws Exception {
        System.out.print("\nInforme o CPF do cliente a ser atualizado: ");
        String cpf = scanner.nextLine();

        Cliente clienteExistente = clienteController.buscarClientePorCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));

        System.out.println("Atualizando cliente: " + clienteExistente.getNome());

        System.out.print("Novo nome [" + clienteExistente.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            nome = clienteExistente.getNome();
        }

        System.out.print("Novo telefone [" + clienteExistente.getTelefone() + "]: ");
        String telefone = scanner.nextLine();
        if (telefone.trim().isEmpty()) {
            telefone = clienteExistente.getTelefone();
        }

        System.out.print("Novo endereço [" + clienteExistente.getEndereco() + "]: ");
        String endereco = scanner.nextLine();
        if (endereco.trim().isEmpty()) {
            endereco = clienteExistente.getEndereco();
        }

        Cliente clienteAtualizado = new Cliente(nome, cpf, telefone, endereco);
        clienteController.atualizarCliente(cpf, clienteAtualizado);
        System.out.println("Cliente atualizado com sucesso!");
    }

    private void removerCliente() throws ClienteNaoEncontradoException {
        System.out.print("\nInforme o CPF do cliente a ser removido: ");
        String cpf = scanner.nextLine();

        clienteController.removerCliente(cpf);
        System.out.println("Cliente removido com sucesso!");
    }

    private void buscarClientePorCpf() {
        System.out.print("\nInforme o CPF do cliente para busca: ");
        String cpf = scanner.nextLine();

        Optional<Cliente> clienteOpt = clienteController.buscarClientePorCpf(cpf);
        if (clienteOpt.isPresent()) {
            Cliente c = clienteOpt.get();
            System.out.println("Cliente encontrado:");
            System.out.println("Nome: " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("Telefone: " + c.getTelefone());
            System.out.println("Endereço: " + c.getEndereco());
        } else {
            System.out.println("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    private void salvarClientes(){
        try {
            clienteController.salvar();
            System.out.println("Clientes salvos com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
}
