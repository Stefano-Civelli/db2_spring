package it.polimi.db2_spring.utility.supportForQueries;

import java.util.Date;
import java.util.List;

public interface ISuspendedOrders {
    OrdersSummary getOrder();

    interface OrdersSummary {
        Long getId();
        Date getCreationTime();
        Double getTotalValue();
        Date getStartingDateOfSubscription();
        IAlert.UserSummary getUser();
        IPurchasesWithName.ServicePKGSummary getServicePKG();
        IPurchasesWithNameAndValidity.KeySummary.ValidityPeriodSummary getPeriod();

        List<OptionalSummary> getOptionalProducts();

        interface OptionalSummary {
            Long getProductCode();
            String getName();
            Double getMonthlyFee();
        }
    }
}
