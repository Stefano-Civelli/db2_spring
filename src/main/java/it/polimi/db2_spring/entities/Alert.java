package it.polimi.db2_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long alertId;

    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @ManyToOne
    @JoinColumn(name = "alert_owner")
    private Users user;
}
