package views;

import controller.ClienteController;
import controller.VeiculoController;
import exception.ClienteNaoEncontradoException;
import exception.ValorInvalidoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import model.Cliente;
import model.Veiculo;
import util.Validador;

public class ClienteView {
    private final ClienteController clienteController;
    private final Scanner scanner;

    public ClienteView() {
        VeiculoController veiculoController = new VeiculoController();
        this.clienteController = new ClienteController(veiculoController);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("Bem-vindo à Mecânica! Seu sistema de gerenciamento de clientes e veículos.");
        boolean rodando = true;

        while (rodando) {
            mostrarMenu();
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    cadastrarClienteComVeiculos();
                    break;
                case "2":
                    listarClientes();
                    break;
                case "3":
                    buscarClientePorCpf();
                    break;
                case "4":
                    atualizarCliente();
                    break;
                case "5":
                    removerCliente();
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
        System.out.println("\n===== Menu Cliente =====");
        System.out.println("1. Cadastrar novo cliente com veículo");
        System.out.println("2. Listar todos os clientes");
        System.out.println("3. Buscar cliente por CPF");
        System.out.println("4. Atualizar dados do cliente");
        System.out.println("5. Remover cliente");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarClienteComVeiculos() {
        try {
            System.out.println("\n--- Cadastro de Cliente ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            Validador.validaNome(nome);

            System.out.print("CPF (11 dígitos): ");
            String cpf = scanner.nextLine();
            Validador.validaCpf(cpf);

            System.out.print("Telefone (10 ou 11 dígitos): ");
            String telefone = scanner.nextLine();
            Validador.validarTelefone(telefone);

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();
            Validador.validaStringNaoVazia(endereco, "Endereço");

            List<Veiculo> veiculos = new ArrayList<>();
            boolean adicionarVeiculo = true;

            while (adicionarVeiculo) {
                Veiculo veiculo = criarVeiculoSimples();
                veiculos.add(veiculo);

                System.out.print("Deseja cadastrar outro veículo para este cliente? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                adicionarVeiculo = resposta.equals("s");
            }

            clienteController.cadastrarCliente(nome, cpf, telefone, endereco, veiculos);
            System.out.println("Cliente e veículo(s) cadastrados com sucesso!");

        } catch (ValorInvalidoException | exception.StringVaziaException e) {
            System.out.println("Erro na validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private Veiculo criarVeiculoSimples() {
        System.out.println("\n--- Cadastro de Veículo ---");
        try {
            System.out.print("Tipo [1] Carro [2] Moto: ");
            String tipoVeiculo = scanner.nextLine().trim();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Cor: ");
            String cor = scanner.nextLine();

            System.out.print("Ano (1900-2026): ");
            int ano = Integer.parseInt(scanner.nextLine());

            System.out.print("Placa: ");
            String placa = scanner.nextLine();

            model.enums.StatusServico status = model.enums.StatusServico.PENDENTE;
            model.enums.TipoCombustivel tipoCombustivel = model.enums.TipoCombustivel.FLEX;
            model.enums.TipoServico tipoServico = model.enums.TipoServico.TROCA_OLEO;

            if (tipoVeiculo.equals("1")) {
                System.out.print("Tração: ");
                String tracao = scanner.nextLine();

                System.out.print("Motor (ex: 1.6, 2.0): ");
                float motor = Float.parseFloat(scanner.nextLine());

                System.out.print("Ar condicionado (true/false): ");
                boolean arCondicionado = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Automático (true/false): ");
                boolean automatico = Boolean.parseBoolean(scanner.nextLine());

                return new factory.VeiculoFactory().criarCarro(arCondicionado, automatico, motor, tracao, ano, cor, marca, modelo,
                        placa, status, tipoCombustivel, tipoServico);

            } else if (tipoVeiculo.equals("2")) {
                System.out.print("Carda (true/false): ");
                boolean carda = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Cilindradas: ");
                int cilindradas = Integer.parseInt(scanner.nextLine());

                System.out.print("Freio ABS (true/false): ");
                boolean freioABS = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Número de marchas: ");
                int numMarchas = Integer.parseInt(scanner.nextLine());

                System.out.print("Partida elétrica (true/false): ");
                boolean partidaEletrica = Boolean.parseBoolean(scanner.nextLine());

                return new factory.VeiculoFactory().criarMoto(carda, cilindradas, freioABS, numMarchas, partidaEletrica, ano, cor, marca,
                        modelo, placa, status, tipoCombustivel, tipoServico);

            } else {
                System.out.println("Tipo de veículo inválido, cadastrando como carro padrão.");
                return new factory.VeiculoFactory().criarCarro(false, false, 1.0f, "Dianteira", ano, cor, marca, modelo, placa, status,
                        tipoCombustivel, tipoServico);
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar veículo: " + e.getMessage());
        }

        return null;
    }

    private void listarClientes() {
        System.out.println("\n--- Lista de Clientes Cadastrados ---");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
                List<Veiculo> veiculos = c.getVeiculos();
                if (veiculos != null && !veiculos.isEmpty()) {
                    for (Veiculo v : veiculos) {
                        System.out.println("  - Veículo: " + v.descricaoVeiculo() + ", Status: " + v.getStatus());
                    }
                } else {
                    System.out.println("  (Sem veículos cadastrados)");
                }
            }
        }
    }

    private void buscarClientePorCpf() {
        System.out.print("Informe o CPF do cliente para busca: ");
        String cpf = scanner.nextLine();
        Optional<Cliente> clienteOpt = clienteController.buscarClientePorCpf(cpf);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            System.out.println("Cliente encontrado: " + cliente);
            List<Veiculo> veiculos = cliente.getVeiculos();
            if (veiculos != null && !veiculos.isEmpty()) {
                for (Veiculo v : veiculos) {
                    System.out.println("  - Veículo: " + v.descricaoVeiculo() + ", Status: " + v.getStatus());
                }
            } else {
                System.out.println("  (Sem veículos cadastrados)");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private void atualizarCliente() {
        try {
            System.out.print("Informe o CPF do cliente que deseja atualizar: ");
            String cpf = scanner.nextLine();
            Optional<Cliente> clienteOpt = clienteController.buscarClientePorCpf(cpf);
            if (!clienteOpt.isPresent()) {
                System.out.println("Cliente não encontrado.");
                return;
            }
            Cliente cliente = clienteOpt.get();

            System.out.print("Novo nome (" + cliente.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.trim().isEmpty()) {
                cliente.setNome(nome.trim());
            }

            System.out.print("Novo telefone (" + cliente.getTelefone() + "): ");
            String telefone = scanner.nextLine();
            if (!telefone.trim().isEmpty()) {
                cliente.setTelefone(telefone.trim());
            }

            System.out.print("Novo endereço (" + cliente.getEndereco() + "): ");
            String endereco = scanner.nextLine();
            if (!endereco.trim().isEmpty()) {
                cliente.setEndereco(endereco.trim());
            }

            clienteController.atualizarCliente(cpf, cliente);
            System.out.println("Cliente atualizado com sucesso!");

        } catch (ClienteNaoEncontradoException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }

    private void removerCliente() {
        try {
            System.out.print("Informe o CPF do cliente que deseja remover: ");
            String cpf = scanner.nextLine();
            clienteController.removerCliente(cpf);
            System.out.println("Cliente removido com sucesso!");
        } catch (ClienteNaoEncontradoException e) {
            System.out.println("Cliente não encontrado: " + e.getMessage());
        }
    }
}

