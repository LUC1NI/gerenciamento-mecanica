package exception; 

public class StringVaziaException extends Exception {

    public StringVaziaException(String mensagem){
        super(mensagem);
    }
    public StringVaziaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}