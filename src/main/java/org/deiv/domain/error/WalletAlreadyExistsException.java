package org.deiv.domain.error;

public class WalletAlreadyExistsException extends Exception {

    public WalletAlreadyExistsException() {
        super();
    }

    public WalletAlreadyExistsException(String message) {
        super(message);
    }

    public WalletAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
