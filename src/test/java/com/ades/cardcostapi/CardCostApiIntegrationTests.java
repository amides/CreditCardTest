package com.ades.cardcostapi;


import com.ades.cardcostapi.domain.ClearingCostRecord;
import com.ades.cardcostapi.model.ClearingCostMatrixDAO;
import com.ades.cardcostapi.model.ClearingCostMatrixRepository;
import com.ades.cardcostapi.services.ClearingCostMatrixCRUDService;
import com.ades.cardcostapi.services.ClearingCostMatrixCRUDServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CardCostApiIntegrationTests {

    @MockitoBean
    ClearingCostMatrixRepository clearingCostMatrixRepository;

    @Autowired
    ClearingCostMatrixCRUDService clearingCostMatrixCRUDService;

    @TestConfiguration
    static class ClearingCostMatrixCrudServiceImplTestContextConfig {

        static private ClearingCostMatrixRepository repository;

        ClearingCostMatrixCrudServiceImplTestContextConfig(ClearingCostMatrixRepository repository){
            this.repository = repository;
        }

        @Bean
        public ClearingCostMatrixCRUDService clearingCostMatrixCRUDService() {
            return new ClearingCostMatrixCRUDServiceImpl(repository);
        }
    }

    @BeforeEach
    public void setUp() {
        ClearingCostMatrixDAO clearingCostMatrixDAO = new ClearingCostMatrixDAO("US", 5.0f);
        ClearingCostMatrixDAO clearingCostMatrixDAO1 = new ClearingCostMatrixDAO("GR", 15.0f);
        ClearingCostMatrixDAO clearingCostMatrixDAO2 = new ClearingCostMatrixDAO("CA", 100.0f);

        List<ClearingCostMatrixDAO> result = new ArrayList<>();
        result.add(clearingCostMatrixDAO);
        result.add(clearingCostMatrixDAO1);
        result.add(clearingCostMatrixDAO2);

        Optional<ClearingCostMatrixDAO> optionalRecord = Optional.of(clearingCostMatrixDAO);

        when(clearingCostMatrixRepository.findAll()).thenReturn(result);
        when(clearingCostMatrixRepository.findById(1L)).thenReturn(optionalRecord);
    }

    @Test
    void whenGetAllRecordsFromService_thenReturnListOfCostMatrix() {
        List<ClearingCostRecord> recordsList = clearingCostMatrixCRUDService.getClearingCostRecord();
        assertThat(recordsList).isNotEmpty();
        assertThat(recordsList).hasSize(3);
        assertThat(recordsList.get(0).getIssuingCountry()).isEqualTo("US");
    }

    @Test
    void whenGetById_thenReturnCostMatrix() {
        ClearingCostRecord foundRecord = clearingCostMatrixCRUDService.getClearingCostRecordById(1L);
        assertThat(foundRecord).isNotNull();
        assertThat(foundRecord.getIssuingCountry()).isEqualTo("US");
    }
}
