package views;

import controller.FuncionarioController;
import exception.FuncionarioNaoEncontradoException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import model.Funcionario;

public class FuncionarioView {
    private final FuncionarioController funcionarioController;
    private final Scanner scanner;

    public FuncionarioView(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
        this.scanner = new Scanner(System.in);
    }

    public void menuFuncionario() {
        while (true) {
            System.out.println("\n=== MENU FUNCIONÁRIO ===");
            System.out.println("1. Listar funcionários");
            System.out.println("2. Cadastrar funcionário");
            System.out.println("3. Atualizar funcionário");
            System.out.println("4. Remover funcionário");
            System.out.println("5. Buscar funcionário por CPF");
            System.out.println("6. Salvar funcionários");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            try {
                switch (opcao) {
                    case "1":
                        listarFuncionarios();
                        break;
                    case "2":
                        cadastrarFuncionario();
                        break;
                    case "3":
                        atualizarFuncionario();
                        break;
                    case "4":
                        removerFuncionario();
                        break;
                    case "5":
                        buscarFuncionarioPorCpf();
                        break;
                    case "6":
                        salvarFuncionarios();
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

    private void listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("\nLista de funcionários:");
            for (Funcionario f : funcionarios) {
                System.out.println("- Nome: " + f.getNome() + ", CPF: " + f.getCpf() + ", Telefone: "
                        + f.getTelefone() + ", Cargo: " + f.getCargo() + ", Salário: " + f.getSalario());
            }
        }
    }

    private void cadastrarFuncionario() throws Exception {
        System.out.println("\nCadastrar novo funcionário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF (somente números): ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Salário: ");
        float salario = Float.parseFloat(scanner.nextLine());

        funcionarioController.cadastrarFuncionario(nome, cpf, telefone, cargo, salario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void atualizarFuncionario() throws Exception {
        System.out.print("\nInforme o CPF do funcionário a ser atualizado: ");
        String cpf = scanner.nextLine();

        Funcionario funcionarioExistente = funcionarioController.buscarFuncionarioPorCpf(cpf)
                .orElseThrow(() -> new FuncionarioNaoEncontradoException("Funcionário não encontrado."));

        System.out.println("Atualizando funcionário: " + funcionarioExistente.getNome());

        System.out.print("Novo nome [" + funcionarioExistente.getNome() + "]: ");
        String nome = scanner.nextLine();
        if (nome.trim().isEmpty()) {
            nome = funcionarioExistente.getNome();
        }

        System.out.print("Novo telefone [" + funcionarioExistente.getTelefone() + "]: ");
        String telefone = scanner.nextLine();
        if (telefone.trim().isEmpty()) {
            telefone = funcionarioExistente.getTelefone();
        }

        System.out.print("Novo cargo [" + funcionarioExistente.getCargo() + "]: ");
        String cargo = scanner.nextLine();
        if (cargo.trim().isEmpty()) {
            cargo = funcionarioExistente.getCargo();
        }

        System.out.print("Novo salário [" + funcionarioExistente.getSalario() + "]: ");
        String salarioStr = scanner.nextLine();
        float salario;
        if (salarioStr.trim().isEmpty()) {
            salario = funcionarioExistente.getSalario();
        } else {
            salario = Float.parseFloat(salarioStr);
        }

        Funcionario funcionarioAtualizado = new Funcionario(nome, cpf, telefone, cargo, salario);
        funcionarioController.atualizarFuncionario(cpf, funcionarioAtualizado);
        System.out.println("Funcionário atualizado com sucesso!");
    }

    private void removerFuncionario() throws FuncionarioNaoEncontradoException {
        System.out.print("\nInforme o CPF do funcionário a ser removido: ");
        String cpf = scanner.nextLine();

        funcionarioController.removerFuncionario(cpf);
        System.out.println("Funcionário removido com sucesso!");
    }

    private void buscarFuncionarioPorCpf() {
        System.out.print("\nInforme o CPF do funcionário para busca: ");
        String cpf = scanner.nextLine();

        Optional<Funcionario> funcionarioOpt = funcionarioController.buscarFuncionarioPorCpf(cpf);
        if (funcionarioOpt.isPresent()) {
            Funcionario f = funcionarioOpt.get();
            System.out.println("Funcionário encontrado:");
            System.out.println("Nome: " + f.getNome());
            System.out.println("CPF: " + f.getCpf());
            System.out.println("Telefone: " + f.getTelefone());
            System.out.println("Cargo: " + f.getCargo());
            System.out.println("Salário: " + f.getSalario());
        } else {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
        }
    }

    private void salvarFuncionarios() {
        try {
            funcionarioController.salvar();
            System.out.println("Funcionários salvos com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar funcionários: " + e.getMessage());
        }
    }
}
