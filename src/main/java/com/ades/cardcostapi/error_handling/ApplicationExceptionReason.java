package com.ades.cardcostapi.error_handling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionReason implements ExceptionPolicy{
    COUNTRY_CODE_PROPERTY_NOT_EXISTS("Property '%s' for object '%s' doesn't exists");

    private final String code = ApplicationExceptionReason.class.getSimpleName();
    private final String message;
}
