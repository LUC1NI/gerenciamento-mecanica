package views;

import controller.FuncionarioController;
import exception.FuncionarioNaoEncontradoException;
import exception.VeiculoNaoEncontradoException;
import java.util.List;
import java.util.Scanner;
import model.Funcionario;
import model.Veiculo;

public class FuncionarioView {
    private FuncionarioController funcionarioController;
    private Scanner scanner;

    public FuncionarioView(FuncionarioController funcionarioController) throws Exception{
        this.funcionarioController = funcionarioController;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== Menu Funcionário ===");
            System.out.println("1. Cadastrar Funcionário");
            System.out.println("2. Listar Funcionários");
            System.out.println("3. Atualizar Funcionário");
            System.out.println("4. Remover Funcionário");
            System.out.println("5. Gerenciar Veículos Responsáveis");
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
                    cadastrarFuncionario();
                    break;
                case 2:
                    listarFuncionarios();
                    break;
                case 3:
                    atualizarFuncionario();
                    break;
                case 4:
                    removerFuncionario();
                    break;
                case 5:
                    gerenciarVeiculosResponsaveis();
                    break;
                case 0:
                    System.out.println("Saindo do menu Funcionário.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void cadastrarFuncionario() {
        try {
            System.out.println("\n=== Cadastro de Funcionário ===");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();
            System.out.print("Salário: ");
            float salario = Float.parseFloat(scanner.nextLine());

            Funcionario funcionario = new Funcionario(nome, cpf, telefone, cargo, salario, null);
            funcionarioController.cadastrarFuncionario(nome, cpf, telefone, cargo, salario, null);
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    private void listarFuncionarios() {
        System.out.println("\n=== Lista de Funcionários ===");
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f : funcionarios) {
                System.out.println(f);
            }
        }
    }

    private void atualizarFuncionario() {
        try {
            System.out.println("\n=== Atualizar Funcionário ===");
            System.out.print("Informe o CPF do funcionário a ser atualizado: ");
            String cpf = scanner.nextLine();
            Funcionario funcionario = funcionarioController.buscarFuncionarioPorCpf(cpf).orElse(null);
            if (funcionario == null) {
                System.out.println("Funcionário não encontrado.");
                return;
            }
            System.out.print("Novo nome (" + funcionario.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                funcionario.setNome(nome);
            }
            System.out.print("Novo telefone (" + funcionario.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.isEmpty()) {
                funcionario.setTelefone(telefone);
            }
            System.out.print("Novo cargo (" + funcionario.getCargo() + "): ");
            String cargo = scanner.nextLine();
            if (!cargo.isEmpty()) {
                funcionario.setCargo(cargo);
            }
            System.out.print("Novo salário (" + funcionario.getSalario() + "): ");
            String salarioStr = scanner.nextLine();
            if (!salarioStr.isEmpty()) {
                float salario = Float.parseFloat(salarioStr);
                funcionario.setSalario(salario);
            }
            funcionarioController.atualizarFuncionario(cpf, funcionario);
            System.out.println("Funcionário atualizado com sucesso!");
        } catch (FuncionarioNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void removerFuncionario() {
        try {
            System.out.println("\n=== Remover Funcionário ===");
            System.out.print("Informe o CPF do funcionário a ser removido: ");
            String cpf = scanner.nextLine();
            funcionarioController.removerFuncionario(cpf);
            System.out.println("Funcionário removido com sucesso!");
        } catch (FuncionarioNaoEncontradoException e) {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private void gerenciarVeiculosResponsaveis() {
        System.out.println("\n=== Gerenciar Veículos Responsáveis ===");
        System.out.print("Informe o CPF do funcionário: ");
        String cpf = scanner.nextLine();
        try {
            while (true) {
                System.out.println("\n1. Listar Veículos Responsáveis");
                System.out.println("2. Adicionar Veículo Responsável");
                System.out.println("3. Remover Veículo Responsável");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        listarVeiculosResponsaveis(cpf);
                        break;
                    case 2:
                        adicionarVeiculoResponsavel(cpf);
                        break;
                    case 3:
                        removerVeiculoResponsavel(cpf);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (FuncionarioNaoEncontradoException e) {
            System.out.println("Funcionário não encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarVeiculosResponsaveis(String cpf) throws FuncionarioNaoEncontradoException {
        List<Veiculo> veiculos = funcionarioController.listarVeiculosResponsaveis(cpf);
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo responsável encontrado.");
        } else {
            System.out.println("Veículos Responsáveis:");
            for (Veiculo v : veiculos) {
                System.out.println(v.descricaoVeiculo() + " - Status: " + v.getStatus());
            }
        }
    }

    private void adicionarVeiculoResponsavel(String cpf) {
        try {
            System.out.println("=== Adicionar Veículo Responsável ===");
            System.out.print("Placa: ");
            String placa = scanner.nextLine();
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine());
            Veiculo veiculo = new model.Carro(false, false, 1.0f, "Dianteira", ano, "Cor Desconhecida", marca, modelo, placa,
                    model.enums.StatusServico.PENDENTE,
                    model.enums.TipoCombustivel.FLEX,
                    model.enums.TipoServico.TROCA_OLEO);

            funcionarioController.adicionarVeiculoResponsavel(cpf, veiculo);
            System.out.println("Veículo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    private void removerVeiculoResponsavel(String cpf) {
        try {
            System.out.print("Informe a placa do veículo a ser removido: ");
            String placa = scanner.nextLine();
            funcionarioController.removerVeiculoResponsavelPorPlaca(cpf, placa);
            System.out.println("Veículo removido com sucesso!");
        } catch (FuncionarioNaoEncontradoException | VeiculoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

