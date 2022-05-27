package it.polimi.db2_spring.utility.supportForQueries;

import java.util.Date;

public interface IAlert {
    Long getAlertId();
    Double getAmount();
    Date getCreationTime();
    UserSummary getUser();

    interface UserSummary {
        String getUsername();
    }
}
