package it.polimi.db2_spring.utility.supportForQueries;

public interface IPurchasesWithNameAndValidity {
    KeySummary getKey();
    Integer getNumberOfPurchases();

    interface KeySummary {
        IPurchasesWithName.ServicePKGSummary getServicePackage();
        ValidityPeriodSummary getValidityPeriod();

        interface ValidityPeriodSummary {
            Long getId();
            Integer getMonths();
            Float getMonthlyFee();
        }
    }
}
