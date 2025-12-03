package com.oficina.gerenciamento.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.oficina.gerenciamento.enums.StatusServico;
import com.oficina.gerenciamento.enums.TipoCombustivel;
import com.oficina.gerenciamento.enums.TipoServico;
import com.oficina.gerenciamento.enums.TipoTracao; 

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity; 
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
@DiscriminatorColumn(name = "tipo_veiculo")
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "tipo_veiculo", 
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Carro.class, name = "CARRO"),
    @JsonSubTypes.Type(value = Moto.class, name = "MOTO")
}) 
public abstract class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String marca;
    private String cor;
    private String placa;
    private int ano;

    @Enumerated(EnumType.STRING) 
    private StatusServico status;

    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;

    @Enumerated(EnumType.STRING)
    private TipoCombustivel tipoCombustivel;

    @Enumerated(EnumType.STRING)
    private TipoTracao tipoTracao;

    @ManyToOne 
    @JoinColumn(name = "cliente_id") 
    private Cliente cliente;

    @ManyToOne 
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public abstract BigDecimal calcularCustoEstimadoServico();

    public String descricaoVeiculo() {
        return "Veiculo: " + modelo + " " + marca + " " + cor + " " + ano;
    }
}