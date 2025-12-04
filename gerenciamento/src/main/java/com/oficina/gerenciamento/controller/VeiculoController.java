package com.oficina.gerenciamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.gerenciamento.entity.Veiculo;
import com.oficina.gerenciamento.repository.ClienteRepository;
import com.oficina.gerenciamento.repository.FuncionarioRepository;
import com.oficina.gerenciamento.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoExistente = repository.findByPlaca(veiculo.getPlaca());

        if (veiculoExistente.isPresent()) {
            return ResponseEntity.badRequest().body("Erro: Já existe um veículo com a placa " + veiculo.getPlaca());
        }

        if (veiculo.getCliente() != null && !clienteRepository.existsById(veiculo.getCliente().getId())) {
            return ResponseEntity.badRequest().body("Erro: Cliente com ID " + veiculo.getCliente().getId() + " não existe.");
        }

        if (veiculo.getFuncionario() != null && !funcionarioRepository.existsById(veiculo.getFuncionario().getId())) {
            return ResponseEntity.badRequest().body("Erro: Funcionario com ID " + veiculo.getFuncionario().getId() + " não existe.");
        }

        Veiculo novoVeiculo = repository.save(veiculo);
        return ResponseEntity.ok(novoVeiculo);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa) {
        return repository.findByPlaca(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}