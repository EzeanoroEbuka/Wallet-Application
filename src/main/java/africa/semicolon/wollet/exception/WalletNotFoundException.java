package africa.semicolon.wollet.exception;

public class WalletNotFoundException extends ResourceNotFoundException  {
    public WalletNotFoundException(String message) {
        super(message);

    }
}
