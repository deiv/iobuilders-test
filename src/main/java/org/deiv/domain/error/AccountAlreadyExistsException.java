package org.deiv.domain.error;

public class AccountAlreadyExistsException extends Exception {

    public AccountAlreadyExistsException() {
        super();
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
