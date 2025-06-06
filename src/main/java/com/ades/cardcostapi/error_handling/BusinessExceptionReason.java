package com.ades.cardcostapi.error_handling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessExceptionReason implements BusinessExceptionPolicy {

    CARD_RECORD_NOT_FOUND_BY_EXT_REF("CardRecord not found based on the given external reference", HttpStatus.NOT_FOUND);

    private final String code = BusinessExceptionReason.class.getSimpleName();
    private final String message;
    private final HttpStatus httpStatus;
}
