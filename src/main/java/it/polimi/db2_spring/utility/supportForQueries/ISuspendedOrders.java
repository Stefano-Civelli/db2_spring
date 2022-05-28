package it.polimi.db2_spring.utility.supportForQueries;

public interface ISuspendedOrders {
    OrdersSummary getOrder();

    interface OrdersSummary {
        Long getId();
        IAlert.UserSummary getUser();
    }
}
