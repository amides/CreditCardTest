package com.ades.cardcostapi.controllers;

import com.ades.cardcostapi.domain.PaymentCardCostRequest;
import com.ades.cardcostapi.domain.PaymentCardCostResponse;
import com.ades.cardcostapi.error_handling.ApplicationException;
import com.ades.cardcostapi.error_handling.ApplicationExceptionReason;
import com.ades.cardcostapi.error_handling.BusinessException;
import com.ades.cardcostapi.services.ClearingCostMatrixCRUDService;
import com.ades.cardcostapi.domain.ClearingCostRecord;
import com.ades.cardcostapi.services.PaymentCardCostService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClearingCostController {

    private final ClearingCostMatrixCRUDService clearingCostMatrixCRUDService;
    private final PaymentCardCostService paymentCardCostService;
    private final Logger log = LoggerFactory.getLogger(ClearingCostController.class);

    ClearingCostController(PaymentCardCostService paymentCardCostService,
                           ClearingCostMatrixCRUDService clearingCostMatrixCRUDService) {

        this.clearingCostMatrixCRUDService = clearingCostMatrixCRUDService;
        this.paymentCardCostService = paymentCardCostService;
    }

    @GetMapping("/clearing-cost-matrix")
    public List<ClearingCostRecord> allClearingCostMatrix() {
        return clearingCostMatrixCRUDService.getClearingCostRecord();
    }

    @PostMapping("/clearing-cost-matrix")
    public void newClearingCostMatrix(@Valid @RequestBody ClearingCostRecord record) {
        clearingCostMatrixCRUDService.createClearingCostRecord(record);
    }

    @GetMapping("/clearing-cost-matrix/{id}")
    ClearingCostRecord oneClearingCostMatrix(@PathVariable Long id) {
        ClearingCostRecord record = clearingCostMatrixCRUDService.getClearingCostRecordById(id);
        return clearingCostMatrixCRUDService.getClearingCostRecordById(id);
    }

    @PutMapping("clearing-cost-matrix/{id}")
    void updateClearingCostMatrix(@PathVariable Long id, @Valid @RequestBody ClearingCostRecord record) {
        clearingCostMatrixCRUDService.updateClearingCostRecord(id, record);
    }

    @DeleteMapping("/clearing-cost-matrix/{id}")
    public void deleteClearingCostMatrix(@Valid @PathVariable Long id) {
        clearingCostMatrixCRUDService.deleteClearingCostRecord(id);
    }

   @PostMapping("/payment-cards-cost")
    public PaymentCardCostResponse paymentCardCost(@Valid @RequestBody PaymentCardCostRequest paymentRequest){
        PaymentCardCostResponse response = paymentCardCostService.getPaymentCardCost(paymentRequest.getCard_number());
        if (response == null) {
            throw new ApplicationException(ApplicationExceptionReason.COUNTRY_CODE_PROPERTY_NOT_EXISTS,
                    "The payment card country code can't be found" + paymentRequest.getCard_number());
        }

        return response;
    }
}
