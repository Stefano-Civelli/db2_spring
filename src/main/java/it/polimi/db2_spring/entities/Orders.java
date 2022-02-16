package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Date dateOfCreation;
    private Time hourOfCreation;
    private Boolean stateOfSubscription;
    private double totalValue;
    @Temporal(TemporalType.DATE)
    private Date startingDateOfSubscription;
    private Boolean isRejected;

    @ManyToOne
    @JoinColumn(name = "order_owner")
    @JsonBackReference(value = "boia")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "servicePKG")
    private ServicePKG servicePKG;
}