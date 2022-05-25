package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.ValidityPeriod;

import java.util.List;

public interface IValidityPeriodService {
    List<ValidityPeriod> getPeriodsList(int limit);
}
