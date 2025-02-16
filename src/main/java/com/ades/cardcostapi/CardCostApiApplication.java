package com.ades.cardcostapi;

import com.ades.cardcostapi.model.ClearingCostMatrixDAO;
import com.ades.cardcostapi.model.ClearingCostMatrixRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CardCostApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardCostApiApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ClearingCostMatrixRepository repository) {

        return args -> {
            repository.deleteAll();

            String[] issuingCountries = {"US", "GR", "CA", "Others"};
            float[] costs = {5.0f, 15.0f, 550.0f, 10.0f};

            for(int i = 0; i < issuingCountries.length; i++)
            {
                ClearingCostMatrixDAO dao = new ClearingCostMatrixDAO();
                dao.setCardIssuingCountryCode(issuingCountries[i]);
                dao.setClearingCost(costs[i]);

                repository.save(dao);
            }
        };

    }

}
