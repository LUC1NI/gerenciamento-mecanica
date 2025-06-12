package util;

import java.util.List;
import java.util.stream.Collectors;
import model.Cliente;
import model.Funcionario;
import model.Veiculo;

public class VeiculoRepository {
    private List<Veiculo> veiculos;
    
    public VeiculoRepository(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void adicionar(Veiculo v) {
        veiculos.add(v);
    }

    public boolean removerPorPlaca(String placa) {
        return veiculos.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
    }

    public Veiculo buscarPorPlaca(String placa) {
        return veiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst()
                .orElse(null);
    }

    public List<Veiculo> listarTodos() {
        return veiculos;
    }

    public List<Veiculo> listarPorCliente(Cliente cliente) {
        return veiculos.stream()
                .filter(v -> v.getCliente().equals(cliente))
                .collect(Collectors.toList());
    }

    public List<Veiculo> listarPorFuncionario(Funcionario funcionario) {
        return veiculos.stream()
                .filter(v -> v.getFuncionario().equals(funcionario))
                .collect(Collectors.toList());
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

}
