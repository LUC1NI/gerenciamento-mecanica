package com.oficina.gerenciamento.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oficina.gerenciamento.entity.Carro;
import com.oficina.gerenciamento.entity.Veiculo;
import com.oficina.gerenciamento.repository.VeiculoRepository;

@ExtendWith(MockitoExtension.class) 
class VeiculoServiceTest {

    @Mock 
    private VeiculoRepository repository;

    @InjectMocks 
    private VeiculoService service;

    @Test
    void deveCadastrarVeiculoComSucessoQuandoPlacaNaoExiste() {

        Carro carro = new Carro();
        carro.setPlaca("ABC-1234");

        Mockito.when(repository.findByPlaca("ABC-1234")).thenReturn(Optional.empty());
        
        Mockito.when(repository.save(Mockito.any(Veiculo.class))).thenReturn(carro);

        Veiculo resultado = service.cadastrar(carro);

        Assertions.assertNotNull(resultado); 
        Assertions.assertEquals("ABC-1234", resultado.getPlaca());
        
        Mockito.verify(repository, Mockito.times(1)).save(carro);
    }

    @Test
    void naoDeveCadastrarVeiculoQuandoPlacaJaExiste() {
        Carro carroNovo = new Carro();
        carroNovo.setPlaca("DUPLICADA");

        Carro carroNoBanco = new Carro(); 
        carroNoBanco.setId(1L);

        Mockito.when(repository.findByPlaca("DUPLICADA")).thenReturn(Optional.of(carroNoBanco));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(carroNovo);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }
}