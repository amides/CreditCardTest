package com.ades.cardcostapi.error_handling;

public interface ExceptionPolicy {
    String getCode();
    String getMessage();
}
