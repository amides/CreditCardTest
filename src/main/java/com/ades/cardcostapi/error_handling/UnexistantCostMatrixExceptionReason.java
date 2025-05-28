package com.ades.cardcostapi.error_handling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnexistantCostMatrixExceptionReason implements ExceptionPolicy {

    COST_MATRIX_NOT_EXISTS("Cost Matrix doesn't exist");

    private final String code = UnexistantCostMatrixExceptionReason.class.getSimpleName();
    private final String message;
}
