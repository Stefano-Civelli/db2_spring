package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IValidityPeriodService;
import it.polimi.db2_spring.entities.ValidityPeriod;
import it.polimi.db2_spring.repo.ValidityPeriodRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ValidityPeriodService implements IValidityPeriodService {

    private final ValidityPeriodRepo validityPeriodRepo;

    @Override
    public List<ValidityPeriod> getPeriodsList(int limit) {
        log.info("fetching Service Package list");
        if(limit == 0)
            return validityPeriodRepo.findAll();
        return validityPeriodRepo.findAll().stream().limit(limit).collect(Collectors.toList());
    }
}
