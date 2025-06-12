package util;

import exception.StringVaziaException;

public class Validador {
    
    public static void validaStringNaoVazia(String valor, String nomeCampo) throws StringVaziaException{
        if (valor == null || valor.trim().isEmpty()) {
            throw new StringVaziaException(nomeCampo + " não pode ser vazio.");
        }
    }
}
