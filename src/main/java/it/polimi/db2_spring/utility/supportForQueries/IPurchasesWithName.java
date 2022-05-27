package it.polimi.db2_spring.utility.supportForQueries;

public interface IPurchasesWithName {
    ServicePKGSummary getServicePackage();
    Integer getNumberOfPurchases();

    interface ServicePKGSummary {
        String getName();
        Long getId();
    }
}
