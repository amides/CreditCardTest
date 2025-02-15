package com.ades.cardcostapi.error_handling;

import org.springframework.http.HttpStatus;

public enum BusinessExceptionReason implements BusinessExceptionPolicy {

    CARD_RECORD_NOT_FOUND_BY_EXT_REF("CardRecord not found based on the given external reference", HttpStatus.NOT_FOUND);

    private final String code = BusinessExceptionReason.class.getSimpleName();
    private final String message;
    private final HttpStatus httpStatus;

    BusinessExceptionReason(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    };

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
