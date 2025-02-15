package com.ades.cardcostapi.error_handling;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

@Getter
@Setter
public class BusinessException extends RuntimeException
                    implements BusinessExceptionPolicy {

    protected final HttpStatus httpStatus;
    protected final String message;
    protected final String code;

    public BusinessException(final BusinessExceptionReason reason) {
        this.httpStatus = reason.getHttpStatus();
        this.message = reason.getMessage();
        this.code = reason.getCode();
    }

    public BusinessException(final BusinessExceptionReason reason,
                             final HttpStatus overridingHttpStatus) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
        this.httpStatus = overridingHttpStatus;
    }

    public BusinessException(final BusinessExceptionReason reason,
                             final Object... parameters) {

        if (parameters != null) {
            this.message = format(reason.getMessage(), parameters);
        } else {
            this.message = reason.getMessage();
        }

        this.code = reason.getCode();
        this.httpStatus = reason.getHttpStatus();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    public String toString() {
        return format("BusinessException(code=%s, message=%s, httpStatus=%s)", this.getCode(), this.getMessage(),
                this.getHttpStatus().value());
    }

    @Override
    public HttpStatus getHttpStatus() {
        return null;
    }

    @Override
    public String getCode() {
        return "";
    }
}
