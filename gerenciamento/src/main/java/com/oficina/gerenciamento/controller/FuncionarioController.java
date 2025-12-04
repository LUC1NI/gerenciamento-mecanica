package com.oficina.gerenciamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficina.gerenciamento.dto.FuncionarioRequestDTO;
import com.oficina.gerenciamento.dto.FuncionarioResponseDTO;
import com.oficina.gerenciamento.entity.Funcionario;
import com.oficina.gerenciamento.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {


    @Autowired
    private FuncionarioRepository repository;

    @GetMapping
    public List<FuncionarioResponseDTO> listarTodos() {
        List<Funcionario> funcionarios = repository.findAll();
        return funcionarios.stream()
                .map(FuncionarioResponseDTO::new)
                .toList();
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> cadastrar(@RequestBody FuncionarioRequestDTO request) {
        
        Funcionario funcionarioParaSalvar = request.toEntity();
        
        Funcionario funcionarioSalvo = repository.save(funcionarioParaSalvar);
        
        return ResponseEntity.ok(new FuncionarioResponseDTO(funcionarioSalvo));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        Optional<Funcionario> funcionario = repository.findById(id);
        return funcionario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}