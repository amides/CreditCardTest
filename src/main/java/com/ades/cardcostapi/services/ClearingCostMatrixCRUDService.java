package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.ClearingCostRecord;

import java.util.List;

public interface ClearingCostMatrixCRUDService {

    void createClearingCostRecord(ClearingCostRecord clearingCostRecord);

    void updateClearingCostRecord(Long id, ClearingCostRecord clearingCostRecord);

    void deleteClearingCostRecord(Long id);

    List<ClearingCostRecord> getClearingCostRecord();

    ClearingCostRecord getClearingCostRecordById(Long id);
}
