package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.BinResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LookUpBinServiceImpl implements LookUpBinService {

    private final RestTemplate restTemplate;
    private final Map<String, BinResponse> binResponsesMapCache = new HashMap<>();

    @Override
    public BinResponse getTheCountryCode(String card_number) {
        //Getting only the BIN number(first 6 digits)
        int BIN_DIGIT_SIZE = 6;
        String bin = card_number.substring(0, BIN_DIGIT_SIZE);

        if (!binResponsesMapCache.isEmpty()
                && binResponsesMapCache.containsKey(bin)) {

            log.info("Cached response ...");
            return binResponsesMapCache.get(bin);
        }

        //Requests are throttled at 5 per hour with a burst allowance of 5.
        // If you hit the speed limit the service will return a 429 http status code.
        //Taken from the API webpage
        String url = "https://lookup.binlist.net/";
        url = url + bin;

        BinResponse binResponse;

        try{
            binResponse = restTemplate.getForObject(url, BinResponse.class);
            if (binResponse == null || binResponse.getCountry() == null) {
                return null;
            }
        }
        catch (RuntimeException e){
            log.error(e.getMessage());
            return null;
        }

        binResponsesMapCache.put(bin, binResponse);
        return binResponse;
    }
}
