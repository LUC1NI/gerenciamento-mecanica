package views;

import controller.ClienteController;
import controller.FuncionarioController;
import controller.VeiculoController;
import exception.VeiculoNaoEncontradoException;
import factory.PessoaFactory;
import java.util.List;
import java.util.Scanner;
import model.Cliente;
import model.Funcionario;
import model.Veiculo;

public class VeiculoConsoleView {
    private final VeiculoController veiculoController;
    private final ClienteController clienteController;
    private final FuncionarioController funcionarioController;
    private final Scanner scanner;

    public VeiculoConsoleView(VeiculoController veiculoController, ClienteController clienteController,
            FuncionarioController funcionarioController, Scanner scanner) {
        this.veiculoController = veiculoController;
        this.clienteController = clienteController;
        this.funcionarioController = funcionarioController;
        this.scanner = scanner;
    }

    public void menuVeiculo() {
        while (true) {
            System.out.println("\n=== MENU VEÍCULO ===");
            System.out.println("1. Listar veículos");
            System.out.println("2. Cadastrar veículo");
            System.out.println("3. Atualizar veículo");
            System.out.println("4. Remover veículo");
            System.out.println("5. Buscar veículo por placa");
            System.out.println("6. Salvar veículos");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1":
                        listarVeiculos();
                        break;
                    case "2":
                        criarVeiculo();
                        break;
                    case "3":
                        atualizarVeiculo();
                        break;
                    case "4":
                        removerVeiculo();
                        break;
                    case "5":
                        buscarVeiculoPorPlaca();
                        break;
                    case "6":
                        salvarVeiculos();
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

    private void listarVeiculos() {
        List<Veiculo> veiculos = veiculoController.listarVeiculos();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("\nLista de veículos:");
            for (Veiculo v : veiculos) {
                System.out.println("- Modelo: " + v.getModelo() + ", Marca: " + v.getMarca() +
                        ", Placa: " + v.getPlaca() + ", Cliente: " + (v.getCliente() != null ? v.getCliente().getNome() : "N/A") +
                        ", Funcionário: " + (v.getFuncionario() != null ? v.getFuncionario().getNome() : "N/A"));
            }
        }
    }

    private void criarVeiculo() throws Exception {
        System.out.println("\nCadastrar novo veículo:");
        Cliente cliente = selecionarOuCriarCliente();
        Funcionario funcionario = selecionarOuCriarFuncionario();
        System.out.println("Escolha o tipo de veículo:");
        System.out.println("1. Carro");
        System.out.println("2. Moto");
        System.out.print("Opção: ");
        String tipoVeiculo = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        model.enums.StatusServico statusServico = escolherStatusServico();
        model.enums.TipoServico tipoServico = escolherTipoServico();
        model.enums.TipoCombustivel tipoCombustivel = escolherTipoCombustivel();
        model.enums.TipoTracao tipoTracao = escolherTipoTracao();
        if (tipoVeiculo.equals("1")) {
            System.out.print("Tem ar condicionado? (s/n): ");
            boolean arCondicionado = scanner.nextLine().trim().equalsIgnoreCase("s");
            System.out.print("É automático? (s/n): ");
            boolean automatico = scanner.nextLine().trim().equalsIgnoreCase("s");
            System.out.print("Potência do motor (ex: 1.6): ");
            float motor = Float.parseFloat(scanner.nextLine());
            veiculoController.cadastrarCarro(modelo, marca, cor, placa, ano, statusServico, tipoServico, tipoCombustivel, tipoTracao, cliente, funcionario, arCondicionado, automatico, motor);
        } else if (tipoVeiculo.equals("2")) {
            System.out.print("Cilindradas: ");
            int cilindradas = Integer.parseInt(scanner.nextLine());
            System.out.print("Número de marchas: ");
            int numMarchas = Integer.parseInt(scanner.nextLine());
            System.out.print("Tem freio ABS? (s/n): ");
            boolean freioABS = scanner.nextLine().trim().equalsIgnoreCase("s");
            System.out.print("Tem carda? (s/n): ");
            boolean carda = scanner.nextLine().trim().equalsIgnoreCase("s");
            System.out.print("Tem partida elétrica? (s/n): ");
            boolean partidaEletrica = scanner.nextLine().trim().equalsIgnoreCase("s");
            veiculoController.cadastrarMoto(modelo, marca, cor, placa, ano, statusServico, tipoServico, tipoCombustivel, tipoTracao, cliente, funcionario, cilindradas, numMarchas, freioABS, carda, partidaEletrica);
        } else {
            System.out.println("Tipo de veículo inválido. Cadastro cancelado.");
            return;
        }
        System.out.println("Veículo cadastrado com sucesso!");
    }

    private void salvarVeiculos() {
        try {
            veiculoController.salvar();
            System.out.println("Veículos salvos com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar veículos: " + e.getMessage());
        }
    }

    private model.enums.StatusServico escolherStatusServico() {
        System.out.println("\nEscolha o status do serviço:");
        for (model.enums.StatusServico status : model.enums.StatusServico.values()) {
            System.out.println(status.getId() + ". " + status.getNome());
        }
        System.out.print("Opção: ");
        int op = Integer.parseInt(scanner.nextLine());
        return model.enums.StatusServico.ObterstatusServicoPorId(op);
    }

    private model.enums.TipoServico escolherTipoServico() {
        System.out.println("\nEscolha o tipo de serviço:");
        model.enums.TipoServico[] servicos = model.enums.TipoServico.values();
        for (int i = 0; i < servicos.length; i++) {
            System.out.printf("%d. %s - R$ %.2f\n", 
                (i + 1), servicos[i].getDescricao(), servicos[i].getPrecoBase());
        }
        System.out.print("Opção: ");
        int op = Integer.parseInt(scanner.nextLine());
        if (op >= 1 && op <= servicos.length) {
            return servicos[op - 1];
        }
        System.out.println("Opção inválida, será usado padrão TROCA_OLEO");
        return model.enums.TipoServico.TROCA_OLEO;
    }


    private model.enums.TipoCombustivel escolherTipoCombustivel() {
        System.out.println("\nEscolha o tipo de combustível:");
        for (model.enums.TipoCombustivel combustivel : model.enums.TipoCombustivel.values()) {
            System.out.println(combustivel.getId() + ". " + combustivel.getNome());
        }
        System.out.print("Opção: ");
        int op = Integer.parseInt(scanner.nextLine());
        try {
            return model.enums.TipoCombustivel.getIdTipoCombustivel(op);
        } catch (IllegalArgumentException e) {
            System.out.println("Opção inválida, será usado padrão FLEX");
            return model.enums.TipoCombustivel.FLEX;
        }
    }

    private model.enums.TipoTracao escolherTipoTracao() {
        System.out.println("\nEscolha o tipo da Tração:");
        for (model.enums.TipoTracao tracao : model.enums.TipoTracao.values()) {
            System.out.println(tracao.getId() + ". " + tracao.getDescricao());
        }
        System.out.print("Opção: ");
        int op = Integer.parseInt(scanner.nextLine());
        try {
            return model.enums.TipoTracao.getIdTipoTracao(op);
        } catch (IllegalArgumentException e) {
            System.out.println("Opção inválida, será usado padrão 4x2");
            return model.enums.TipoTracao.QUATROXDOIS;
        }
    }

    private Cliente selecionarOuCriarCliente() throws Exception {
        List<Cliente> clientes = clienteController.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado. Por favor, crie um novo cliente.");
            return criarNovoCliente();
        }

        System.out.println("Clientes existentes:");
        int i = 1;
        for (Cliente c : clientes) {
            System.out.println(i + ". " + c.getNome() + " - CPF: " + c.getCpf());
            i++;
        }
        System.out.println(i + ". Criar novo cliente");
        System.out.print("Escolha uma opção para o cliente: ");

        int opcao = Integer.parseInt(scanner.nextLine());
        if (opcao >= 1 && opcao < i) {
            return clientes.get(opcao - 1);
        } else if (opcao == i) {
            return criarNovoCliente();
        } else {
            System.out.println("Opção inválida. Tentando novamente.");
            return selecionarOuCriarCliente();
        }
    }

    private Cliente criarNovoCliente() throws Exception {
        System.out.println("Criar novo cliente:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        clienteController.cadastrarCliente(nome, cpf, telefone, endereco);
        return new PessoaFactory().criarCliente(nome, cpf, telefone, endereco);
    }

    private Funcionario selecionarOuCriarFuncionario() throws Exception {
        List<Funcionario> funcionarios = funcionarioController.listarFuncionarios(); 

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado. Por favor, crie um novo funcionário.");
            return criarNovoFuncionario();
        }

        System.out.println("Funcionários existentes:");
        int i = 1;
        for (Funcionario f : funcionarios) {
            System.out.println(i + ". " + f.getNome() + " - CPF: " + f.getCpf());
            i++;
        }
        System.out.println(i + ". Criar novo funcionário");
        System.out.print("Escolha uma opção para o funcionário: ");

        int opcao = Integer.parseInt(scanner.nextLine());
        if (opcao >= 1 && opcao < i) {
            return funcionarios.get(opcao - 1);
        } else if (opcao == i) {
            return criarNovoFuncionario();
        } else {
            System.out.println("Opção inválida. Tentando novamente.");
            return selecionarOuCriarFuncionario();
        }
    }


    private Funcionario criarNovoFuncionario() throws Exception {
        System.out.println("Criar novo funcionário:");
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
        funcionarioController.cadastrarFuncionario(nome, cpf, telefone, cargo, salario);
        return new PessoaFactory().criarFuncionario(nome, cpf, telefone, cargo, salario);
    }

    private void atualizarVeiculo() throws Exception {
        System.out.print("\nInforme a placa do veículo a ser atualizado: ");
        String placa = scanner.nextLine();

        Veiculo veiculoExistente = veiculoController.buscarVeiculoPorPlaca(placa);
        if (veiculoExistente == null) {
            System.out.println("Veículo com placa '" + placa + "' não encontrado.");
            return;
        }

        System.out.println("Atualizando veículo: " + veiculoExistente.getModelo() + " - " + veiculoExistente.getPlaca());

        System.out.print("Novo modelo [" + veiculoExistente.getModelo() + "]: ");
        String modelo = scanner.nextLine();
        if (modelo.trim().isEmpty()) {
            modelo = veiculoExistente.getModelo();
        }

        System.out.print("Nova marca [" + veiculoExistente.getMarca() + "]: ");
        String marca = scanner.nextLine();
        if (marca.trim().isEmpty()) {
            marca = veiculoExistente.getMarca();
        }

        System.out.print("Nova cor [" + veiculoExistente.getCor() + "]: ");
        String cor = scanner.nextLine();
        if (cor.trim().isEmpty()) {
            cor = veiculoExistente.getCor();
        }
    }

    private void removerVeiculo() throws VeiculoNaoEncontradoException {
        System.out.print("\nInforme a placa do veículo a ser removido: ");
        String placa = scanner.nextLine();

        veiculoController.removerVeiculo(placa);
        System.out.println("Veículo removido com sucesso!");
    }

    private void buscarVeiculoPorPlaca() {
        System.out.print("\nInforme a placa do veículo para busca: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = veiculoController.buscarVeiculoPorPlaca(placa);
        if (veiculo != null) {
            System.out.println("Veículo encontrado:");
            System.out.println("Modelo: " + veiculo.getModelo());
            System.out.println("Marca: " + veiculo.getMarca());
            System.out.println("Cor: " + veiculo.getCor());
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("Cliente: " + (veiculo.getCliente() != null ? veiculo.getCliente().getNome() : "N/A"));
            System.out.println("Funcionário: " + (veiculo.getFuncionario() != null ? veiculo.getFuncionario().getNome() : "N/A"));
        } else {
            System.out.println("Veículo com placa '" + placa + "' não encontrado.");
        }
    }
}
