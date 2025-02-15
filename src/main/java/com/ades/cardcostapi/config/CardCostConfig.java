package com.ades.cardcostapi.config;


import com.ades.cardcostapi.services.LookUpBinService;
import com.ades.cardcostapi.services.LookUpBinServiceImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CardCostConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public LookUpBinService lookUpBinService(RestTemplate restTemplate){
        return new LookUpBinServiceImpl(restTemplate);
    }
}

