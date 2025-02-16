package com.ades.cardcostapi.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCardCostRequest implements Serializable {

    private String card_number;

}
