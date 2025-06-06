package controller;

import exception.ValorInvalidoException;
import exception.VeiculoNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Veiculo;

public class VeiculoController {
    private final List<Veiculo> veiculos;

    public VeiculoController() {
        this.veiculos = new ArrayList<>();
    }

    public void cadastrarVeiculo(Veiculo veiculo) throws ValorInvalidoException {
        if (buscarVeiculoPorPlaca(veiculo.getPlaca()).isPresent()) {
            throw new ValorInvalidoException("Já existe um veículo cadastrado com a placa " + veiculo.getPlaca());
        }
        veiculos.add(veiculo);
    }

    public Optional<Veiculo> buscarVeiculoPorPlaca(String placa) {
        return veiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
    }

    public void atualizarVeiculo(String placa, Veiculo veiculoAtualizado) throws VeiculoNaoEncontradoException {
        Veiculo existente = buscarVeiculoPorPlaca(placa)
                .orElseThrow(() -> new VeiculoNaoEncontradoException("Veículo com placa " + placa + " não encontrado."));
        int index = veiculos.indexOf(existente);
        veiculos.set(index, veiculoAtualizado);
    }

    public void removerVeiculo(String placa) throws VeiculoNaoEncontradoException {
        boolean removed = veiculos.removeIf(v -> v.getPlaca().equalsIgnoreCase(placa));
        if (!removed) {
            throw new VeiculoNaoEncontradoException("Veículo com placa " + placa + " não encontrado.");
        }
    }

    public List<Veiculo> listarVeiculos() {
        return new ArrayList<>(veiculos);
    }
}

