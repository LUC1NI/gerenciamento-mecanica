// João Vitor Lucini - 39026957
// Tiago Moreno - 38034662
// Leonardo Reis - 37770179
// Gabriel Benites - 38841142
// Felipe Pinheiro - 38325969

import controller.ClienteController;
import controller.FuncionarioController;
import controller.VeiculoController;
import java.util.List;
import model.Cliente;
import model.Funcionario;
import util.VeiculoRepository;
import views.MenuPrincipalView;

public class App {
    public static void main(String[] args) throws Exception {
        ClienteController clienteController = new ClienteController();
        FuncionarioController funcionarioController = new FuncionarioController();
        VeiculoRepository veiculoRepository = new VeiculoRepository();
        VeiculoController veiculoController = new VeiculoController(veiculoRepository);

        try {
            List<Cliente> clientesCarregados = ClienteController.carregar();
            if (clientesCarregados != null) {
                for (Cliente c : clientesCarregados) {
                    clienteController.cadastrarCliente(c.getNome(), c.getCpf(), c.getTelefone(), c.getEndereco());
                }
            }
            System.out.println("Clientes carregados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }

        try {
            List<Funcionario> funcionariosCarregados = FuncionarioController.carregar();
            if (funcionariosCarregados != null) {
                for (Funcionario f : funcionariosCarregados) {
                    funcionarioController.cadastrarFuncionario(f.getNome(), f.getCpf(), f.getTelefone(), f.getCargo(), f.getSalario());
                }
            }
            System.out.println("Funcionários carregados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
        }

        try {
            veiculoController.carregar();
            System.out.println("Veículos carregados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar veículos: " + e.getMessage());
        }

        MenuPrincipalView menu = new MenuPrincipalView(clienteController, funcionarioController, veiculoController);
        menu.exibirMenu();
    }
}

