package views;

import java.util.Scanner;

public class MecanicaView {
    private final ClienteView clienteView;
    private final FuncionarioView funcionarioView;
    private final Scanner scanner;

    public MecanicaView() throws Exception {
        this.clienteView = new ClienteView();
        this.funcionarioView = new FuncionarioView();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("Bem-vindo ao Sistema de Gerenciamento da Mecânica!");
        boolean rodando = true;
        
        while (rodando) {
            mostrarMenu();
            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1":
                    clienteView.iniciar();
                    break;
                case "2":
                    funcionarioView.menu();
                    break;
                case "0":
                    rodando = false;
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n===== Menu Principal =====");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Funcionários");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}

