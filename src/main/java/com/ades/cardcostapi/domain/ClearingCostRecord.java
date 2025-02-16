package com.ades.cardcostapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClearingCostRecord {

    private Long id;

    private String issuingCountry;

    private float cost;

}
