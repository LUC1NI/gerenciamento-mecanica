package exception;

public class VeiculoNaoEncontradoException extends Exception {

    public VeiculoNaoEncontradoException() {
        super("Veículo não encontrado.");
    }

    public VeiculoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
