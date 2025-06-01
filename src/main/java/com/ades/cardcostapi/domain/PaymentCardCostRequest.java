package com.ades.cardcostapi.domain;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCardCostRequest implements Serializable {
    private String card_number;
}
