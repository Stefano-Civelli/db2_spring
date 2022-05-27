package it.polimi.db2_spring.utility.supportForQueries;

public interface IRevenuePerPackage {
    IPurchasesWithName.ServicePKGSummary getServicePackage();
    Double getRevenueWithOptional();
    Double getRevenueWithoutOptional();
}
