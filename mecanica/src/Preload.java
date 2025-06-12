import controller.ClienteController;
import controller.FuncionarioController;
import controller.VeiculoController;
import model.Cliente;
import model.Funcionario;
import model.enums.StatusServico;
import model.enums.TipoCombustivel;
import model.enums.TipoServico;
import model.enums.TipoTracao;
import util.VeiculoRepository;

public class Preload {

    public static ClienteController preloadClientes() throws Exception {
        ClienteController clienteController = new ClienteController(new java.util.ArrayList<>());

        clienteController.cadastrarCliente("João Silva", "12345678900", "11999999999", "Rua A, 123");
        clienteController.cadastrarCliente("Maria Oliveira", "98765432100", "11988888888", "Rua B, 456");

        return clienteController;
    }

    public static FuncionarioController preloadFuncionarios() throws Exception {
        FuncionarioController funcionarioController = new FuncionarioController(new java.util.ArrayList<>());

        funcionarioController.cadastrarFuncionario("Carlos Pereira", "11122233344", "11977777777", "Mecânico", 2500.0f);
        funcionarioController.cadastrarFuncionario("Ana Souza", "55566677788", "11966666666", "Atendente", 1800.0f);

        return funcionarioController;
    }

    public static VeiculoController preloadVeiculos(ClienteController clienteController, FuncionarioController funcionarioController) throws Exception {
        VeiculoRepository veiculoRepository = new VeiculoRepository(new java.util.ArrayList<>());
        VeiculoController veiculoController = new VeiculoController(veiculoRepository);

        Cliente cliente1 = clienteController.listarClientes().get(0);
        Funcionario funcionario1 = funcionarioController.listarFuncionarios().get(0);

        veiculoController.cadastrarCarro("Gol", "Volkswagen", "Prata", "ABC1234", 2010,
                StatusServico.PENDENTE, TipoServico.TROCA_OLEO, TipoCombustivel.FLEX, TipoTracao.QUATROXDOIS,
                cliente1, funcionario1, true, false, 1.6f);

        veiculoController.cadastrarMoto("CB 500", "Honda", "Preta", "XYZ5678", 2018,
                StatusServico.PENDENTE, TipoServico.REVISAO_GERAL, TipoCombustivel.GASOLINA, TipoTracao.DOISXUM,
                cliente1, funcionario1, 500, 6, true, false, true);

        return veiculoController;
    }
}
