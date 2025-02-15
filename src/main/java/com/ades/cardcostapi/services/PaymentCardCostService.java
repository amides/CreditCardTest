package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.PaymentCardCostResponse;

public interface PaymentCardCostService {
    public abstract PaymentCardCostResponse getPaymentCardCost(String card_number);
}
