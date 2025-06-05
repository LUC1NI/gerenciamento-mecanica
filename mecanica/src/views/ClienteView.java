package views;

import controller.ClienteController;
import java.util.List;
import java.util.Scanner;
import model.Cliente;

public class ClienteView {
    private ClienteController clienteController;
    private Scanner scanner;

    public ClienteView(ClienteController clienteController) {
        this.clienteController = clienteController;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("=== Menu Cliente ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    removerCliente();
                    break;
                case 0:
                    System.out.println("Saindo do menu Cliente.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void cadastrarCliente() {
        System.out.println("=== Cadastro de Cliente ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, telefone, endereco, null);
        clienteController.adicionarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void listarClientes() {
        System.out.println("=== Lista de Clientes ===");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    private void atualizarCliente() {
        System.out.println("=== Atualizar Cliente ===");
        System.out.print("Informe o CPF do cliente a ser atualizado: ");
        String cpf = scanner.nextLine();
        Cliente cliente = clienteController.buscarPorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        System.out.print("Novo nome (" + cliente.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) {
            cliente.setNome(nome);
        }
        System.out.print("Novo telefone (" + cliente.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) {
            cliente.setTelefone(telefone);
        }
        System.out.print("Novo endereço (" + cliente.getEndereco() + "): ");
        String endereco = scanner.nextLine();
        if (!endereco.isEmpty()) {
            cliente.setEndereco(endereco);
        }
        if (clienteController.atualizarCliente(cpf, cliente)) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar cliente.");
        }
    }

    private void removerCliente() {
        System.out.println("=== Remover Cliente ===");
        System.out.print("Informe o CPF do cliente a ser removido: ");
        String cpf = scanner.nextLine();
        if (clienteController.removerCliente(cpf)) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
