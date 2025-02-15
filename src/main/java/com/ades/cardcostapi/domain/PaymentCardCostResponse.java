package com.ades.cardcostapi.domain;

import org.springframework.stereotype.Component;

@Component
public class PaymentCardCostResponse {
    private String country;
    private float cost;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
