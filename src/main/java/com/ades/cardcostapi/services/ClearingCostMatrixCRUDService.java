package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.ClearingCostRecord;

import java.util.List;

public interface ClearingCostMatrixCRUDService {
    public abstract void createClearingCostRecord(ClearingCostRecord clearingCostRecord);
    public abstract void updateClearingCostRecord(Long id, ClearingCostRecord clearingCostRecord);
    public abstract void deleteClearingCostRecord(Long id);
    public abstract List<ClearingCostRecord> getClearingCostRecord();
    public abstract ClearingCostRecord getClearingCostRecordById(Long id);
}
