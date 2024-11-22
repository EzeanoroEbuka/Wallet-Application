package africa.semicolon.wollet.exception;

public class TransactionNotFoundException extends ResourceNotFoundException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
