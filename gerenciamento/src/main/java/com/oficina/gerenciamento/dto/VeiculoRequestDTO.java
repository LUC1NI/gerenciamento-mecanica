package com.oficina.gerenciamento.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.oficina.gerenciamento.entity.Cliente;
import com.oficina.gerenciamento.entity.Funcionario;
import com.oficina.gerenciamento.entity.Veiculo;
import com.oficina.gerenciamento.enums.StatusServico;
import com.oficina.gerenciamento.enums.TipoCombustivel;
import com.oficina.gerenciamento.enums.TipoServico;
import com.oficina.gerenciamento.enums.TipoTracao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "tipo_veiculo", 
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CarroRequestDTO.class, name = "CARRO"),
    @JsonSubTypes.Type(value = MotoRequestDTO.class, name = "MOTO")
})
public abstract class VeiculoRequestDTO {

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @NotBlank(message = "A placa é obrigatória")
    private String placa;

    @NotBlank(message = "A cor é obrigatória")
    private String cor;

    @NotNull(message = "O status é obrigatório")
    private StatusServico status;

    @NotNull(message = "O tipo de serviço é obrigatório")
    private TipoServico tipoServico;
    
    @NotNull(message = "O tipo de combustível é obrigatório")
    private TipoCombustivel tipoCombustivel;
    
    @NotNull(message = "O tipo de tração é obrigatório")
    private TipoTracao tipoTracao;

    @NotNull(message = "O cliente é obrigatório")
    private Long clienteId;
    
    @NotNull(message = "O funcionário é obrigatório")
    private Long funcionarioId;

    public abstract Veiculo toEntity();
    
    protected void preencherDadosComuns(Veiculo veiculo) {
        veiculo.setModelo(this.modelo);
        veiculo.setMarca(this.marca);
        veiculo.setAno(this.ano);
        veiculo.setPlaca(this.placa);
        veiculo.setCor(this.cor);
        veiculo.setStatus(this.status);
        veiculo.setTipoServico(this.tipoServico);
        veiculo.setTipoCombustivel(this.tipoCombustivel);
        veiculo.setTipoTracao(this.tipoTracao);
        
        Cliente c = new Cliente(); c.setId(this.clienteId);
        veiculo.setCliente(c);
        
        Funcionario f = new Funcionario(); f.setId(this.funcionarioId);
        veiculo.setFuncionario(f);
    }
}