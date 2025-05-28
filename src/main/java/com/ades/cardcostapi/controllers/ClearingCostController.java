package com.ades.cardcostapi.controllers;

import com.ades.cardcostapi.services.ClearingCostMatrixCRUDService;
import com.ades.cardcostapi.domain.ClearingCostRecord;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clearing")
public class ClearingCostController {

    private final ClearingCostMatrixCRUDService clearingCostMatrixCRUDService;

    public ClearingCostController(ClearingCostMatrixCRUDService clearingCostMatrixCRUDService) {
        this.clearingCostMatrixCRUDService = clearingCostMatrixCRUDService;
    }

    @GetMapping
    public List<ClearingCostRecord> allClearingCostMatrix() {
        return clearingCostMatrixCRUDService.getClearingCostRecord();
    }

    @PostMapping
    public ClearingCostRecord createNewClearingCostMatrix(@Valid @RequestBody ClearingCostRecord record) {
        return clearingCostMatrixCRUDService.createClearingCostRecord(record);
    }

    @GetMapping("/{id}")
    ClearingCostRecord oneClearingCostMatrix(@PathVariable Long id) {
        return clearingCostMatrixCRUDService.getClearingCostRecordById(id);
    }

    @PutMapping("/{id}")
    void updateClearingCostMatrix(@PathVariable Long id, @Valid @RequestBody ClearingCostRecord record) {
        clearingCostMatrixCRUDService.updateClearingCostRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public void deleteClearingCostMatrix(@Valid @PathVariable Long id) {
        clearingCostMatrixCRUDService.deleteClearingCostRecord(id);
    }

}
