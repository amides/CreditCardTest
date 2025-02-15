package com.ades.cardcostapi.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClearingCostMatrixRepository extends JpaRepository<ClearingCostMatrixDAO, Long> {
    List<ClearingCostMatrixDAO> findByCardIssuingCountryCode(String countryCode);
}
