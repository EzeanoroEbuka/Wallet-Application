package africa.semicolon.wollet.exception;

public class CustomerEmailAlreadyExistException extends ResourceNotFoundException {
    public CustomerEmailAlreadyExistException(String message) {
        super(message);
    }
}
