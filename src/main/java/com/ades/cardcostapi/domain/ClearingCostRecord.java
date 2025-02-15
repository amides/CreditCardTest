package com.ades.cardcostapi.domain;

import org.springframework.stereotype.Component;

@Component
public class ClearingCostRecord {

    private Long id;
    private String issuingCountry;
    private float cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
