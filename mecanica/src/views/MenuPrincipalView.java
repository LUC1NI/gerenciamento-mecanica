package views;

import controller.ClienteController;
import controller.FuncionarioController;
import controller.VeiculoController;
import java.util.Scanner;

public class MenuPrincipalView {
    private final Scanner scanner;
    private final ClienteController clienteController;
    private final FuncionarioController funcionarioController;
    private final VeiculoController veiculoController;
    private final ClienteView clienteView;
    private final FuncionarioView funcionarioView;
    private final VeiculoConsoleView veiculoView;

    public MenuPrincipalView(ClienteController clienteController, FuncionarioController funcionarioController, VeiculoController veiculoController) {
        this.scanner = new Scanner(System.in);
        this.clienteController = clienteController;
        this.funcionarioController = funcionarioController;
        this.veiculoController = veiculoController;
        this.clienteView = new ClienteView(clienteController);
        this.funcionarioView = new FuncionarioView(funcionarioController);
        this.veiculoView = new VeiculoConsoleView(veiculoController, clienteController,
                funcionarioController, scanner);
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL - Oficina Mecânica ===");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Veículos");
            System.out.println("4. Salvar tudo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    clienteView.menuCliente();
                    break;
                case "2":
                    funcionarioView.menuFuncionario();
                    break;
                case "3":
                    veiculoView.menuVeiculo();
                    break;
                case "4":
                    salvarTudo();
                    break;
                case "0":
                    System.out.println("Encerrando o sistema. Até logo!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void salvarTudo() {
        try {
            clienteController.salvar();
            funcionarioController.salvar();
            veiculoController.salvar();
            System.out.println("Todos os dados foram salvos com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}

