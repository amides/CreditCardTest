package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.BinResponse;

public interface LookUpBinService {
    public abstract BinResponse getTheCountryCode(String card_number);
}
