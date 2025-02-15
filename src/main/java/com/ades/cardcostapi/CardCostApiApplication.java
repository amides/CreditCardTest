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

            repository.save(new ClearingCostMatrixDAO("US", 5.0f));
            repository.save(new ClearingCostMatrixDAO("GR", 15.0f));
            repository.save(new ClearingCostMatrixDAO("CA", 550.0f));
            repository.save(new ClearingCostMatrixDAO("Others", 10.0f));
        };

    }

}
