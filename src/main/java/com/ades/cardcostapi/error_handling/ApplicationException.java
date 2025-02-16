package com.ades.cardcostapi.error_handling;

import lombok.Getter;
import lombok.Setter;

import static java.lang.String.format;

@Getter
@Setter
public class ApplicationException  extends RuntimeException implements ExceptionPolicy {

    private final String code;
    private final String message;

    public ApplicationException(final ApplicationExceptionReason reason) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
    }

    public ApplicationException(final ApplicationExceptionReason reason, final Object... parameters) {
        if (parameters != null) {
            this.message = format(reason.getMessage(), parameters);
        } else {
            this.message = reason.getMessage();
        }

        this.code = reason.getCode();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    public String toString() {
        return format("ApplicationException(code=%s, message=%s)", this.getCode(), this.getMessage());
    }
}
