package com.oficina.gerenciamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oficina.gerenciamento.entity.Veiculo;
import com.oficina.gerenciamento.repository.VeiculoRepository;

@Service 
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Veiculo cadastrar(Veiculo veiculo) {
        if (repository.findByPlaca(veiculo.getPlaca()).isPresent()) {
            throw new IllegalArgumentException("Já existe um veículo com a placa " + veiculo.getPlaca());
        }

        return repository.save(veiculo);
    }
    
    public Optional<Veiculo> buscarPorPlaca(String placa) {
        return repository.findByPlaca(placa);
    }
    
    public void deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Veículo não encontrado para exclusão");
        }
    }
}