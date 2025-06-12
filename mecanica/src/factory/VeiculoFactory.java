package factory;

import exception.ValorInvalidoException;
import model.Carro;
import model.Cliente;
import model.Funcionario;
import model.Moto;
import model.enums.StatusServico;
import model.enums.TipoCombustivel;
import model.enums.TipoServico;
import model.enums.TipoTracao;
import util.Validador;

public class VeiculoFactory {
    public Carro criarCarro(String modelo, String marca, String cor, String placa, int ano, StatusServico status,
            TipoServico tipoServico, TipoCombustivel tipoCombustivel, TipoTracao tipoTracao, Cliente cliente,
            Funcionario funcionario, boolean arCondicionado, boolean automatico, float motor) throws Exception{
        validaAno(ano);
        Validador.validaStringNaoVazia(cor, "Cor");
        Validador.validaStringNaoVazia(marca, "Marca");
        Validador.validaStringNaoVazia(modelo, "Modelo");
        Validador.validaStringNaoVazia(placa, "Placa");
        validaMotor(motor);
        validaEnums(status, tipoCombustivel, tipoServico);
        return new Carro(modelo, marca, cor, placa, ano, status, tipoServico, tipoCombustivel, tipoTracao, cliente, funcionario, arCondicionado, automatico, motor);
    }

    public Moto criarMoto(String modelo, String marca, String cor, String placa, int ano, StatusServico status,TipoServico tipoServico, TipoCombustivel tipoCombustivel, TipoTracao tipoTracao, Cliente cliente,
    Funcionario funcionario, int cilindradas, int numMarchas, boolean freioABS, boolean carda,
    boolean partidaEletrica) throws Exception{
        validaAno(ano);
        validaCilindradas(cilindradas);
        validaMarchas(numMarchas);
        Validador.validaStringNaoVazia(cor, "Cor");
        Validador.validaStringNaoVazia(marca, "Marca");
        Validador.validaStringNaoVazia(modelo, "Modelo");
        Validador.validaStringNaoVazia(placa, "Placa");
        validaEnums(status, tipoCombustivel, tipoServico);
        return new Moto(modelo, marca, cor, placa, ano, status, tipoServico, tipoCombustivel, tipoTracao, cliente, funcionario, cilindradas, numMarchas, freioABS, carda, partidaEletrica);
    }

    private void validaAno(int ano) throws ValorInvalidoException{
        if (ano < 1900 || ano > 2026) {
            throw new ValorInvalidoException("Ano do veículo inválido: " + ano);
        }
    }

    private void validaMotor(float motor) throws ValorInvalidoException{
        if (motor <= 0.0f) {
            throw new ValorInvalidoException("Motor deve ter valor maior que zero.");
        }
    }

    private void validaEnums(StatusServico status, TipoCombustivel combustivel, TipoServico servico) throws ValorInvalidoException{
        if (status == null) {
            throw new ValorInvalidoException("Status do serviço não pode ser nulo.");
        }
        if (combustivel == null) {
            throw new ValorInvalidoException("Tipo de combustível não pode ser nulo.");
        }
        if (servico == null) {
            throw new ValorInvalidoException("Tipo de serviço não pode ser nulo.");
        }
    }

    private void validaCilindradas(int cilindradas) throws ValorInvalidoException{
        if (cilindradas <= 0) {
            throw new ValorInvalidoException("Cilindradas devem ser maiores que zero.");
        }
    }

    private void validaMarchas(int marchas) throws ValorInvalidoException{
        if (marchas <= 0) {
            throw new ValorInvalidoException("Número de marchas deve ser maior que zero.");
        }
    }
}

