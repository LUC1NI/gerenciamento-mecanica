package com.oficina.gerenciamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.gerenciamento.dto.VeiculoRequestDTO;
import com.oficina.gerenciamento.entity.Veiculo;
import com.oficina.gerenciamento.service.VeiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @GetMapping
    public List<Veiculo> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrar(@RequestBody @Valid VeiculoRequestDTO request) {
        
        Veiculo veiculoParaSalvar = request.toEntity();
        
        Veiculo novoVeiculo = service.cadastrar(veiculoParaSalvar);
        
        return ResponseEntity.ok(novoVeiculo);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa) {
        return service.buscarPorPlaca(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}