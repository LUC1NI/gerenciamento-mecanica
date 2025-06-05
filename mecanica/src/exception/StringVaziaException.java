package exception; 

public class StringVaziaException extends RuntimeException {

    public StringVaziaException(String mensagem){
        super(mensagem);
    }
    public StringVaziaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}