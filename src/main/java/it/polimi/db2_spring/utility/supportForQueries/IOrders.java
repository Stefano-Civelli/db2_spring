package it.polimi.db2_spring.utility.supportForQueries;

import java.util.Date;
import java.util.List;

public interface IOrders {
    IPurchasesWithName.ServicePKGSummary getServicePKG();
    Date getCreationTime();
    Date getStartingDateOfSubscription();
    Double getTotalValue();
    IPurchasesWithNameAndValidity.KeySummary.ValidityPeriodSummary getPeriod();
    List<OptionalSummary> getOptionalProducts();

    interface OptionalSummary {
        String getName();
    }
}
