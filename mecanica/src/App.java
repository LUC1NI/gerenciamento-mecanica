// João Vitor Lucini - 39026957
// Tiago Moreno - 38034662
// Leonardo Reis - 37770179
// Gabriel Benites - 38841142
// Felipe Pinheiro - 38325969

import controller.ClienteController;
import controller.FuncionarioController;
import controller.VeiculoController;
import views.MenuPrincipalView;

public class App {
    public static void main(String[] args) throws Exception {
        ClienteController clienteController = Preload.preloadClientes();
        FuncionarioController funcionarioController = Preload.preloadFuncionarios();
        VeiculoController veiculoController = Preload.preloadVeiculos(clienteController, funcionarioController);

        MenuPrincipalView menu = new MenuPrincipalView(clienteController, funcionarioController, veiculoController);
        menu.exibirMenu();
    }
}
