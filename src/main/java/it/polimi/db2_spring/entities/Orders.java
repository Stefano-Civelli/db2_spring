package it.polimi.db2_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Time hour;
    private boolean stateOfSubscription;

    @ManyToOne
    @JoinColumn(name = "servicePKG")
    private ServicePKG servicePKG;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user_;

}
