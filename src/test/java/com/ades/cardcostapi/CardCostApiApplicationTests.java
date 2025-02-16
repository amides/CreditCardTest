package com.ades.cardcostapi;

import com.ades.cardcostapi.controllers.ClearingCostController;
import com.ades.cardcostapi.model.ClearingCostMatrixDAO;
import com.ades.cardcostapi.model.ClearingCostMatrixRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CardCostApiApplicationTests {

    @Autowired
    private ClearingCostController clearingCostController;

    @Autowired
    ClearingCostMatrixRepository clearingCostMatrixRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(this.clearingCostController).isNotNull();
    }

   @Test
    public void givenClearingCostMatrixRecords_WhenGetRecords_ReturnJsonArray()
            throws Exception {

       clearingCostMatrixRepository.deleteAll();

        ClearingCostMatrixDAO record = new ClearingCostMatrixDAO();
        record.setClearingCost(5.0f);
        record.setCardIssuingCountryCode("US");

        clearingCostMatrixRepository.save(record);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clearing")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].issuingCountry", Matchers.is("US")));
    }

    @Test
    public void givenPaymentCardNumber_ReturnCountryCode_AND_Cost()
            throws Exception {

        clearingCostMatrixRepository.deleteAll();

        ClearingCostMatrixDAO record = new ClearingCostMatrixDAO();
        record.setCardIssuingCountryCode("US");
        record.setClearingCost(5.0f);

        ClearingCostMatrixDAO record1 = new ClearingCostMatrixDAO();
        record1.setCardIssuingCountryCode("GR");
        record1.setClearingCost(15.0f);

        ClearingCostMatrixDAO record2 = new ClearingCostMatrixDAO();
        record2.setCardIssuingCountryCode("Others");
        record2.setClearingCost(10.0f);

        clearingCostMatrixRepository.save(record);
        clearingCostMatrixRepository.save(record1);
        clearingCostMatrixRepository.save(record2);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/payment")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\n" +
                        "    \"card_number\": \"45101440\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", Matchers.is("CA")))
                .andExpect(jsonPath("$.cost", Matchers.is(10.0)));
    }
}
