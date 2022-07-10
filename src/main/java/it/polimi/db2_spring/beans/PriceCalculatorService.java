package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.entities.ValidityPeriod;
import it.polimi.db2_spring.repo.OptionalProductRepo;
import it.polimi.db2_spring.repo.ValidityPeriodRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PriceCalculatorService {
    private final ValidityPeriodRepo validityPeriodRepo;
    private final OptionalProductRepo optionalProductRepo;

    public int getTotalValue(Long period, List<Long> selectedOptionalProducts) throws EntityNotFoundException {
        int value = 0;

        ValidityPeriod validityFromDb = validityPeriodRepo.getById(period);
        value += validityFromDb.getMonths() * validityFromDb.getMonthlyFee();

        for(long optional : selectedOptionalProducts) {
            OptionalProduct optionalFromDb = optionalProductRepo.getById(optional);
            value += validityFromDb.getMonths() * optionalFromDb.getMonthlyFee();
        }

        return value;
    }
}
