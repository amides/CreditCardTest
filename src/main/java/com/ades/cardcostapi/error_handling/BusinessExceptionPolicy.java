package com.ades.cardcostapi.error_handling;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionPolicy
        extends ExceptionPolicy {

    HttpStatus getHttpStatus();

}
