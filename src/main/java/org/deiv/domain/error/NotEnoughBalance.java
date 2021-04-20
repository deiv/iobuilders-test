package org.deiv.domain.error;

public class NotEnoughBalance extends Exception {

    public NotEnoughBalance() {
        super();
    }

    public NotEnoughBalance(String message) {
        super(message);
    }

    public NotEnoughBalance(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughBalance(Throwable cause) {
        super(cause);
    }
}
