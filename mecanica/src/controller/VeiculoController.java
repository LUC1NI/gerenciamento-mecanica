package controller;

import java.util.ArrayList;
import java.util.List;
import model.Veiculo;

public class VeiculoController {
    private final List<Veiculo> veiculos;

    public VeiculoController() {
        this.veiculos = new ArrayList<>();
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return new ArrayList<>(veiculos);
    }

    public boolean atualizarVeiculo(String placa, Veiculo veiculoAtualizado) {
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            if (v.getPlaca().equals(placa)) {
                veiculos.set(i, veiculoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean removerVeiculo(String placa) {
        return veiculos.removeIf(v -> v.getPlaca().equals(placa));
    }

    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }
}
