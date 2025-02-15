package com.ades.cardcostapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class ClearingCostMatrixDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cardIssuingCountryCode;
    private float clearingCost;

    public ClearingCostMatrixDAO() {}

    public ClearingCostMatrixDAO(String cardIssuingCountryCode,
                                 float clearingCost) {
        this.cardIssuingCountryCode = cardIssuingCountryCode;
        this.clearingCost = clearingCost;
    }

    public Long getId() {
        return id;
    }

    public String getCardIssuingCountryCode() {
        return cardIssuingCountryCode;
    }

    public float getClearingCost() {
        return clearingCost;
    }

    public void setCardIssuingCountryCode(String cardIssuingCountryCode) {
        this.cardIssuingCountryCode = cardIssuingCountryCode;
    }

    public void setClearingCost(float clearingCost) {
        this.clearingCost = clearingCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClearingCostMatrixDAO that))
            return false;

        return Float.compare(clearingCost, that.clearingCost) == 0
                && Objects.equals(id, that.id)
                && Objects.equals(cardIssuingCountryCode, that.cardIssuingCountryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardIssuingCountryCode, clearingCost);
    }

    @Override
    public String toString() {
        return "ClearingCostControllerDAO{" +
                "id=" + id +
                ", cardIssuingCountryCode='" + cardIssuingCountryCode + '\'' +
                ", clearingCost=" + clearingCost +
                '}';
    }
}
