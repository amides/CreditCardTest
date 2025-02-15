package com.ades.cardcostapi.controllers;

public class ClearingCostMatrixNotFoundException extends RuntimeException {

    public ClearingCostMatrixNotFoundException(Long id) {
        super("Could not find a ClearingCostMatrix with id " + id);
    }
}
