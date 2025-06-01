package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.ClearingCostRecord;
import com.ades.cardcostapi.error_handling.ApplicationException;
import com.ades.cardcostapi.error_handling.ApplicationExceptionReason;
import com.ades.cardcostapi.error_handling.BusinessException;
import com.ades.cardcostapi.error_handling.BusinessExceptionReason;
import com.ades.cardcostapi.model.ClearingCostMatrixDAO;
import com.ades.cardcostapi.model.ClearingCostMatrixRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClearingCostMatrixCRUDServiceImpl implements ClearingCostMatrixCRUDService {

    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());
    private final ClearingCostMatrixRepository clearingCostMatrixRepository;

    @Override
    public ClearingCostRecord createClearingCostRecord(ClearingCostRecord clearingCostRecord) throws IllegalArgumentException {

        if (!ISO_COUNTRIES.contains(clearingCostRecord.getIssuingCountry())) {
            log.info("createClearingCostRecord: Invalid issuing country: {}", clearingCostRecord.getIssuingCountry());
            throw new BusinessException(BusinessExceptionReason.CARD_RECORD_NOT_FOUND_BY_EXT_REF,
                    "Invalid issuing country: " + clearingCostRecord.getIssuingCountry());
        }

        ClearingCostMatrixDAO clearingCostMatrixDAO = new ClearingCostMatrixDAO();
        clearingCostMatrixDAO.setClearingCost(clearingCostRecord.getCost());
        clearingCostMatrixDAO.setCardIssuingCountryCode(clearingCostRecord.getIssuingCountry());
        clearingCostMatrixRepository.save(clearingCostMatrixDAO);

        ClearingCostRecord record = new ClearingCostRecord();
        record.setCost(clearingCostRecord.getCost());
        record.setIssuingCountry(clearingCostRecord.getIssuingCountry());
        record.setId(clearingCostMatrixDAO.getId());

        return record;
    }

    @Override
    public ClearingCostRecord updateClearingCostRecord(Long id, ClearingCostRecord newClearingCostRecord) {

        if (!ISO_COUNTRIES.contains(newClearingCostRecord.getIssuingCountry())
                && !newClearingCostRecord.getIssuingCountry().equals("Others"))
        {
            log.info("updateClearingCostRecord: Invalid issuing country: {}", newClearingCostRecord.getIssuingCountry());
            throw new BusinessException(BusinessExceptionReason.CARD_RECORD_NOT_FOUND_BY_EXT_REF,
                    "Invalid issuing country: " + newClearingCostRecord.getIssuingCountry());
        }


        ClearingCostMatrixDAO dao = clearingCostMatrixRepository.findById(id)
                .map(clearingCostMatrixDAO -> {
                    clearingCostMatrixDAO.setCardIssuingCountryCode(newClearingCostRecord.getIssuingCountry());
                    clearingCostMatrixDAO.setClearingCost(newClearingCostRecord.getCost());
                    return clearingCostMatrixRepository.save(clearingCostMatrixDAO);
                }).orElseGet(() -> {
                    ClearingCostMatrixDAO clearingCostMatrixDAO = new ClearingCostMatrixDAO();
                    clearingCostMatrixDAO.setClearingCost(newClearingCostRecord.getCost());
                    clearingCostMatrixDAO.setCardIssuingCountryCode(newClearingCostRecord.getIssuingCountry());
                    return clearingCostMatrixRepository.save(clearingCostMatrixDAO);
                });

        ClearingCostRecord record = new ClearingCostRecord();
        record.setId(dao.getId());
        record.setCost(dao.getClearingCost());
        record.setIssuingCountry(dao.getCardIssuingCountryCode());

        return record;
    }

    @Override
    public void deleteClearingCostRecord(Long id) {
        ClearingCostMatrixDAO dao = clearingCostMatrixRepository.findById(id).orElse(null);
        if (dao == null) {
            log.info("deleteClearingCostRecord: Invalid id: {}", id);
            throw new ApplicationException(ApplicationExceptionReason.COST_MATRIX_NOT_EXISTS,
                    "deleteClearingCostRecord: ClearingCostMatrixDAO with id:" + id + " doesn't exist");
        }

        clearingCostMatrixRepository.deleteById(id);
    }

    @Override
    public List<ClearingCostRecord> getClearingCostRecord() {
        List<ClearingCostRecord> result = new ArrayList<>();
        List<ClearingCostMatrixDAO> daoList = clearingCostMatrixRepository.findAll();

        for (ClearingCostMatrixDAO dao : daoList) {
            ClearingCostRecord record = new ClearingCostRecord();
            record.setId(dao.getId());
            record.setCost(dao.getClearingCost());
            record.setIssuingCountry(dao.getCardIssuingCountryCode());

            result.add(record);
        }

        return result;
    }

    @Override
    public ClearingCostRecord getClearingCostRecordById(Long id) {
        ClearingCostMatrixDAO record = clearingCostMatrixRepository.findById(id).orElse(null);
        if (record == null) {
            log.info("getClearingCostRecordById: ClearingCostMatrixDAO with id: {} doesn't exist", id);
            throw new BusinessException(BusinessExceptionReason.CARD_RECORD_NOT_FOUND_BY_EXT_REF,
                    "getClearingCostRecordById: ClearingCostMatrixDAO with id:" + id + " doesn't exist");
        }

        ClearingCostRecord result = new ClearingCostRecord();
        result.setId(id);
        result.setCost(record.getClearingCost());
        result.setIssuingCountry(record.getCardIssuingCountryCode());

        return result;
    }
}
