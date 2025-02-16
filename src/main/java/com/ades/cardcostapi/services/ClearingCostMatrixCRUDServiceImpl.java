package com.ades.cardcostapi.services;

import com.ades.cardcostapi.domain.ClearingCostRecord;
import com.ades.cardcostapi.error_handling.BusinessException;
import com.ades.cardcostapi.error_handling.BusinessExceptionReason;
import com.ades.cardcostapi.model.ClearingCostMatrixDAO;
import com.ades.cardcostapi.model.ClearingCostMatrixRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class ClearingCostMatrixCRUDServiceImpl implements ClearingCostMatrixCRUDService {

    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());
    private final ClearingCostMatrixRepository clearingCostMatrixRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClearingCostMatrixCRUDServiceImpl.class);

    public ClearingCostMatrixCRUDServiceImpl(ClearingCostMatrixRepository clearingCostMatrixRepository) {
        this.clearingCostMatrixRepository = clearingCostMatrixRepository;
    }

    @Override
    public void createClearingCostRecord(ClearingCostRecord clearingCostRecord) throws IllegalArgumentException {

        if (!ISO_COUNTRIES.contains(clearingCostRecord.getIssuingCountry())) {
            logger.info("createClearingCostRecord: Invalid issuing country: {}", clearingCostRecord.getIssuingCountry());
            throw new BusinessException(BusinessExceptionReason.CARD_RECORD_NOT_FOUND_BY_EXT_REF,
                    "Invalid issuing country: " + clearingCostRecord.getIssuingCountry());

        }
        ClearingCostMatrixDAO clearingCostMatrixDAO = new ClearingCostMatrixDAO();
        clearingCostMatrixDAO.setClearingCost(clearingCostRecord.getCost());
        clearingCostMatrixDAO.setCardIssuingCountryCode(clearingCostRecord.getIssuingCountry());

        clearingCostMatrixRepository.save(clearingCostMatrixDAO);
    }

    @Override
    public void updateClearingCostRecord(Long id, ClearingCostRecord newClearingCostRecord) {

        if (!ISO_COUNTRIES.contains(newClearingCostRecord.getIssuingCountry())
                && !newClearingCostRecord.getIssuingCountry().equals("Others"))
        {
            logger.info("updateClearingCostRecord: Invalid issuing country: {}", newClearingCostRecord.getIssuingCountry());
            throw new BusinessException(BusinessExceptionReason.CARD_RECORD_NOT_FOUND_BY_EXT_REF,
                    "Invalid issuing country: " + newClearingCostRecord.getIssuingCountry());
        }


        clearingCostMatrixRepository.findById(id)//
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
    }

    @Override
    public void deleteClearingCostRecord(Long id) {
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
            logger.info("getClearingCostRecordById: ClearingCostMatrixDAO with id: {} doesn't exist", id);
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
