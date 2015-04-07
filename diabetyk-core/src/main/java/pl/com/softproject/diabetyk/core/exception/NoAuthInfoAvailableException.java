package pl.com.softproject.diabetyk.core.exception;

/**
 * Class NoAuthInfoAvailableException
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public class NoAuthInfoAvailableException extends RuntimeException {

    public NoAuthInfoAvailableException() {

    }

    public NoAuthInfoAvailableException(String message) {

        super(message);
    }

    public NoAuthInfoAvailableException(String message, Throwable cause) {

        super(message, cause);
    }

    public NoAuthInfoAvailableException(Throwable cause) {

        super(cause);
    }

    public NoAuthInfoAvailableException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
    }
}
