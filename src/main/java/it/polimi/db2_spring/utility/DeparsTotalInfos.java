package it.polimi.db2_spring.utility;

import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.entities.ValidityPeriod;
import it.polimi.db2_spring.repo.OptionalProductRepo;
import it.polimi.db2_spring.repo.ValidityPeriodRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeparsTotalInfos {

    private ValidityPeriodRepo validityPeriodRepo;
    private OptionalProductRepo optionalProductRepo;

    private long period;
    private List<Long> selectedOptionalProducts;

    public int getTotalValue() throws EntityNotFoundException {
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
