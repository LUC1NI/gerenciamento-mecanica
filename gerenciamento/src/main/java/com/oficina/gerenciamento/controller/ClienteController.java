package com.oficina.gerenciamento.controller;

import com.oficina.gerenciamento.entity.Cliente;
import com.oficina.gerenciamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes") 
public class ClienteController {

    @Autowired
    private ClienteRepository repository;


    @GetMapping
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = repository.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get()); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente dadosAtualizados) {
        Optional<Cliente> clienteExistente = repository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNome(dadosAtualizados.getNome());
            cliente.setCpf(dadosAtualizados.getCpf());
            cliente.setTelefone(dadosAtualizados.getTelefone());
            cliente.setEndereco(dadosAtualizados.getEndereco());

            repository.save(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}