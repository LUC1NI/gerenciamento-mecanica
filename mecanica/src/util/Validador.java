package util;

import exception.StringVaziaException;
import exception.ValorInvalidoException;

public class Validador {
    public static void validaCpf(String cpf) {
        validaStringNaoVazia(cpf, "CPF");
        if (!cpf.matches("\\d{11}")) {
            throw new ValorInvalidoException("CPF inválido. Deve conter exatamente 11 dígitos numéricos.");
        }
    }

    public static void validaStringNaoVazia(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new StringVaziaException(nomeCampo + " não pode ser vazio.");
        }
    }

    public static void validaNome(String nome){
        validaStringNaoVazia(nome, "nome");
        if (nome.length() < 3 ) {
            throw new ValorInvalidoException("Nome deve ter mais de 3 caracteres.");
        }
    }

    public static void validarTelefone(String telefone) {
        validaStringNaoVazia(telefone, "Telefone");
        if (!telefone.matches("\\d{10,11}")) {
            throw new ValorInvalidoException("Telefone inválido. Deve conter 10 ou 11 dígitos numéricos.");
        }
    }
}
