package com.ades.cardcostapi.error_handling;

public enum ApplicationExceptionReason implements ExceptionPolicy{
    COUNTRY_CODE_PROPERTY_NOT_EXISTS("Property '%s' for object '%s' doesn't exists");

    private final String code = ApplicationExceptionReason.class.getSimpleName();
    private final String message;

    ApplicationExceptionReason(String message) {
        this.message = message;
    }

    public String getCode(){
        return code;
    }

    public String getMessage() {
        return message;
    }

}
