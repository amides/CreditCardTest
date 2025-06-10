package com.ades.cardcostapi.controllers;

import com.ades.cardcostapi.domain.PaymentCardCostRequest;
import com.ades.cardcostapi.domain.PaymentCardCostResponse;
import com.ades.cardcostapi.error_handling.ApplicationException;
import com.ades.cardcostapi.error_handling.ApplicationExceptionReason;
import com.ades.cardcostapi.services.PaymentCardCostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentCardCostService paymentCardCostService;

    @PostMapping
    public ResponseEntity<PaymentCardCostResponse> paymentCardCost(@Valid @RequestBody PaymentCardCostRequest paymentRequest){
        PaymentCardCostResponse response = paymentCardCostService.getPaymentCardCost(paymentRequest.getCard_number());
        if (response == null) {
            throw new ApplicationException(ApplicationExceptionReason.COUNTRY_CODE_PROPERTY_NOT_EXISTS,
                    "The payment card country code can't be found" + paymentRequest.getCard_number());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
