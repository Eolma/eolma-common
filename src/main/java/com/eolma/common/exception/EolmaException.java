package com.eolma.common.exception;

import lombok.Getter;

@Getter
public class EolmaException extends RuntimeException {

    private final ErrorType errorType;
    private final String detail;

    public EolmaException(ErrorType errorType) {
        super(errorType.getTitle());
        this.errorType = errorType;
        this.detail = errorType.getTitle();
    }

    public EolmaException(ErrorType errorType, String detail) {
        super(detail);
        this.errorType = errorType;
        this.detail = detail;
    }
}
