// João Vitor Lucini - 39026957
// Tiago Moreno - 38034662
// Leonardo Reis - 37770179
// Gabriel Benites - 38841142
// Felipe Pinheiro - 38325969

import controller.ClienteController;
import controller.FuncionarioController;
import controller.VeiculoController;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Funcionario;
import model.Veiculo;
import util.VeiculoRepository;
import views.MenuPrincipalView;

public class App {
    public static void main(String[] args) throws Exception {

        ClienteController clienteController = new ClienteController(new ArrayList<Cliente>());
        FuncionarioController funcionarioController = new FuncionarioController(new ArrayList<Funcionario>());
        VeiculoRepository veiculoRepository = new VeiculoRepository(new ArrayList<Veiculo>());
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

        clienteController = Preload.preloadClientes();
        funcionarioController = Preload.preloadFuncionarios();
        veiculoController = Preload.preloadVeiculos(clienteController, funcionarioController);
        
        MenuPrincipalView menu = new MenuPrincipalView(clienteController, funcionarioController, veiculoController);
        menu.exibirMenu();
    }
}
