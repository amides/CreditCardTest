package com.ades.cardcostapi.error_handling;

import com.ades.cardcostapi.error_handling.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class GlobalApiExceptionHandler
        extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalApiExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleUncaughtException(final Exception ex,
                                                          final ServletWebRequest request) {
        logger.error(ex.getMessage(), request.toString());
        final ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponseDto.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleCustomUncaughtBusinessException(final BusinessException ex, final ServletWebRequest request) {
        logger.error(ex.getMessage(), request.toString());

        final ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setStatus(ex.getHttpStatus().value());
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setCode(ex.getCode());

        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponseDto);
    }

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<Object> handleCustomUncaughtApplicationException(final ApplicationException ex,
                                                                           final ServletWebRequest request) {
        logger.error(ex.getMessage(), request.toString());

        final ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(ex.getMessage());
        errorResponseDto.setCode(ex.getCode());

        return ResponseEntity.badRequest().body(errorResponseDto);
    }
}
