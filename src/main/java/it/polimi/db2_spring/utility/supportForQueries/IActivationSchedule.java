package it.polimi.db2_spring.utility.supportForQueries;

import it.polimi.db2_spring.utility.ServiceType;

import java.util.Date;
import java.util.List;

public interface IActivationSchedule {
    List<IOrders.OptionalSummary> getOptionalProducts();
    Date getStartingDateOfSubscription();
    ValidityPeriodSummary getPeriod();
    ServicePKGSummary getServicePKG();
    double getTotalValue();


    interface ServicePKGSummary {
        String getName();
        List<ServicesSummary> getServices ();

        interface ServicesSummary {
            ServiceType getServiceType();
        }
    }

    interface ValidityPeriodSummary {
        Integer getMonths();
    }
}
