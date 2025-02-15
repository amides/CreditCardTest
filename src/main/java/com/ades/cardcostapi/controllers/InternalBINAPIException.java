package com.ades.cardcostapi.controllers;

public class InternalBINAPIException extends RuntimeException {
    public InternalBINAPIException(String cardNumber) {
        super(" Internal api exception with card number: " + cardNumber );
    }
}
