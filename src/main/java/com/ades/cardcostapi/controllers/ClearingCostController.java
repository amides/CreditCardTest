package com.ades.cardcostapi.controllers;

import com.ades.cardcostapi.controllers.dtos.MessageData;
import com.ades.cardcostapi.controllers.dtos.SuccessfulResponseDTO;
import com.ades.cardcostapi.services.ClearingCostMatrixCRUDService;
import com.ades.cardcostapi.domain.ClearingCostRecord;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clearing")
@RequiredArgsConstructor
public class ClearingCostController {

    private final ClearingCostMatrixCRUDService clearingCostMatrixCRUDService;

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
    ClearingCostRecord updateClearingCostMatrix(@PathVariable Long id, @Valid @RequestBody ClearingCostRecord record) {
        return clearingCostMatrixCRUDService.updateClearingCostRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public SuccessfulResponseDTO deleteClearingCostMatrix(@Valid @PathVariable Long id) {
        clearingCostMatrixCRUDService.deleteClearingCostRecord(id);
        MessageData message = new MessageData("Clearing cost matrix deleted");
        return new SuccessfulResponseDTO(message);
    }
}
