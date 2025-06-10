package com.ades.cardcostapi.controllers;

import com.ades.cardcostapi.controllers.dtos.MessageData;
import com.ades.cardcostapi.controllers.dtos.SuccessfulResponseDTO;
import com.ades.cardcostapi.services.ClearingCostMatrixCRUDService;
import com.ades.cardcostapi.domain.ClearingCostRecord;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clearing")
@RequiredArgsConstructor
public class ClearingCostController {

    private final ClearingCostMatrixCRUDService clearingCostMatrixCRUDService;

    @GetMapping
    public ResponseEntity<List<ClearingCostRecord>> allClearingCostMatrix() {
        return new ResponseEntity<>(clearingCostMatrixCRUDService.getClearingCostRecord(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClearingCostRecord> createNewClearingCostMatrix(@Valid @RequestBody ClearingCostRecord record) {
        return new ResponseEntity<>(clearingCostMatrixCRUDService.createClearingCostRecord(record), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<ClearingCostRecord> oneClearingCostMatrix(@PathVariable Long id) {
        return new ResponseEntity<>(clearingCostMatrixCRUDService.getClearingCostRecordById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<ClearingCostRecord> updateClearingCostMatrix(@PathVariable Long id, @Valid @RequestBody ClearingCostRecord record) {
        return new ResponseEntity<>(clearingCostMatrixCRUDService.updateClearingCostRecord(id,record), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponseDTO> deleteClearingCostMatrix(@Valid @PathVariable Long id) {
        clearingCostMatrixCRUDService.deleteClearingCostRecord(id);
        MessageData message = new MessageData("Clearing cost matrix deleted");
        return new ResponseEntity<>(new SuccessfulResponseDTO(message), HttpStatus.OK);
    }
}
