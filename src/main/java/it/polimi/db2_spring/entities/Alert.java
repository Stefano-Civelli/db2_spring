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
    @Column(nullable = false)
    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date creationTime;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "alert_owner")
    private Users user;
}
