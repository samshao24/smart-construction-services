package com.smart.construction.common.exception;

import java.io.Serializable;

public class ControllerException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -3608458198048204963L;

    public ControllerException() {
        super();
    }

    public ControllerException(final String message) {
        super(message);
    }

    public ControllerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
