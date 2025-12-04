package com.oficina.gerenciamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.gerenciamento.dto.ClienteRequestDTO;
import com.oficina.gerenciamento.dto.ClienteResponseDTO;
import com.oficina.gerenciamento.entity.Cliente;
import com.oficina.gerenciamento.repository.ClienteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        var clientes = repository.findAll();
        
        var listaDto = clientes.stream()
                .map(ClienteResponseDTO::new)
                .toList();

        return ResponseEntity.ok(listaDto);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody @Valid ClienteRequestDTO request) {
        Cliente clienteParaSalvar = request.toEntity();
        
        Cliente clienteSalvo = repository.save(clienteParaSalvar);
        
        return ResponseEntity.ok(new ClienteResponseDTO(clienteSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(cliente -> ResponseEntity.ok(new ClienteResponseDTO(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequestDTO request) {
        Optional<Cliente> clienteExistente = repository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            
            cliente.setNome(request.getNome());
            cliente.setCpf(request.getCpf());
            cliente.setTelefone(request.getTelefone());
            cliente.setEndereco(request.getEndereco());

            repository.save(cliente);
            return ResponseEntity.ok(new ClienteResponseDTO(cliente));
        }
        
        return ResponseEntity.notFound().build();
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