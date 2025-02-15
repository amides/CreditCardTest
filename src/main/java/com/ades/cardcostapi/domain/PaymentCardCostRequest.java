package com.ades.cardcostapi.domain;

import org.springframework.stereotype.Component;

@Component
public class PaymentCardCostRequest {
    private String card_number;

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }
}
