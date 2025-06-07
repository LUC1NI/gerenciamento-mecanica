package controller;

import dal.VeiculoDAO;
import exception.ValorInvalidoException;
import exception.VeiculoNaoEncontradoException;
import factory.VeiculoFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Cliente;
import model.Funcionario;
import model.Moto;
import model.Veiculo; 
import model.enums.StatusServico;
import model.enums.TipoCombustivel;
import model.enums.TipoServico;
import util.VeiculoRepository;

public class VeiculoController {
    private final VeiculoRepository veiculoRepository;

    public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public void cadastrarCarro(String modelo, String marca, String cor, String placa, int ano,
                                  StatusServico status, TipoServico tipoServico, TipoCombustivel tipoCombustivel,
                                  Cliente cliente, Funcionario funcionario, String tracao, boolean arCondicionado,
                                  boolean automatico, float motor) throws Exception {
        Veiculo veiculo = new VeiculoFactory().criarCarro(modelo, marca, cor, placa, ano, status, tipoServico, 
        tipoCombustivel, cliente, funcionario, tracao, arCondicionado, automatico, motor);
        if (veiculoRepository.buscarPorPlaca(placa) != null) {
            throw new ValorInvalidoException("Já existe um veículo cadastrado com essa placa.");
        }
        veiculoRepository.adicionar(veiculo);
    }

    public void cadastrarMoto(String modelo, String marca, String cor, String placa, int ano,
                              StatusServico status, TipoServico tipoServico, TipoCombustivel tipoCombustivel,
                              Cliente cliente, Funcionario funcionario, int cilindradas, int numMarchas,
                              boolean freioABS, boolean carda, boolean partidaEletrica) throws Exception {
        Moto moto = new VeiculoFactory().criarMoto(modelo, marca, cor, placa, ano, status, tipoServico, tipoCombustivel, cliente, funcionario, cilindradas, numMarchas, freioABS, carda, partidaEletrica);
        if (veiculoRepository.buscarPorPlaca(placa) != null) {
            throw new ValorInvalidoException("Já existe um veículo cadastrado com essa placa.");
        }
        veiculoRepository.adicionar(moto);
    }

    public void removerVeiculo(String placa) throws VeiculoNaoEncontradoException {
        boolean removido = veiculoRepository.removerPorPlaca(placa);
        if (!removido) {
            throw new VeiculoNaoEncontradoException("Veículo com placa " + placa + " não encontrado.");
        }
    }

    public List<Veiculo> listarVeiculos() {
        return veiculoRepository.listarTodos();
    }

    public Set<Cliente> getClientesUnicosPublic() {
        Set<Cliente> clientes = new HashSet<>();
        for (Veiculo v : veiculoRepository.listarTodos()) {
            if (v.getCliente() != null) {
                clientes.add(v.getCliente());
            }
        }
        return clientes;
    }

    public Set<Funcionario> getFuncionariosUnicosPublic() {
        Set<Funcionario> funcionarios = new HashSet<>();
        for (Veiculo v : veiculoRepository.listarTodos()) {
            if (v.getFuncionario() != null) {
                funcionarios.add(v.getFuncionario());
            }
        }
        return funcionarios;
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        return veiculoRepository.buscarPorPlaca(placa);
    }

    public void salvar() throws IOException {
        VeiculoDAO.salvar(veiculoRepository.listarTodos());
    }

    public void carregar() throws IOException, ClassNotFoundException {
        List<Veiculo> veiculosCarregados = VeiculoDAO.carregar();
        veiculoRepository.setVeiculos(veiculosCarregados);
    }

}
