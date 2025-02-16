package com.ades.cardcostapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class ClearingCostMatrixDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cardIssuingCountryCode;

    private float clearingCost;

    public ClearingCostMatrixDAO(long l, String us, float v) {
    }
}
