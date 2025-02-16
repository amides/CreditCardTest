package com.ades.cardcostapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCardCostResponse {

    private String country;

    private float cost;

}
