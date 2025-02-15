package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.BinResponse;
import com.ades.cardcostapi.domain.PaymentCardCostResponse;
import com.ades.cardcostapi.model.ClearingCostMatrixDAO;
import com.ades.cardcostapi.model.ClearingCostMatrixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentCardCostServiceImpl implements PaymentCardCostService {

    private static final Logger log = LoggerFactory.getLogger(PaymentCardCostServiceImpl.class);
    private final LookUpBinService lookUpBinService;
    private final ClearingCostMatrixRepository clearingCostMatrixRepository;

    public PaymentCardCostServiceImpl( LookUpBinService lookUpBinService,
                                       ClearingCostMatrixRepository clearingCostMatrixRepository) {
        this.lookUpBinService = lookUpBinService;
        this.clearingCostMatrixRepository = clearingCostMatrixRepository;
    }

    @Override
    public PaymentCardCostResponse getPaymentCardCost(String card_number) {
        if (card_number == null || card_number.length() < 8
                || card_number.length() > 19
                || !isNumeric(card_number)) {

            return null;
        }

        BinResponse binResponse = lookUpBinService.getTheCountryCode(card_number);
        if (binResponse == null) {
            log.error("BinResponse is null");
            return null;
        }

        List<ClearingCostMatrixDAO> clearingRecords = clearingCostMatrixRepository
                .findByCardIssuingCountryCode(binResponse.getCountry().getCountryCode());

        if (clearingRecords == null || clearingRecords.isEmpty()) {
            clearingRecords = clearingCostMatrixRepository.findByCardIssuingCountryCode("Others");

            if (clearingRecords == null || clearingRecords.isEmpty()) {
                return null;
            }
        }

        PaymentCardCostResponse response = new PaymentCardCostResponse();
        response.setCost(clearingRecords.get(0).getClearingCost());
        response.setCountry(binResponse.getCountry().getCountryCode());

        return response;
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        else {

            try {
                Long value = Long.parseLong(str);
            }
            catch (NumberFormatException e) {
                return false;
            }

            return true;
        }
    }
}
