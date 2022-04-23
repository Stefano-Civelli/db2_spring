package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.polimi.db2_spring.utility.ValidityPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    private double totalValue;
    @Temporal(TemporalType.DATE)
    private Date startingDateOfSubscription;
    private Boolean isRejected; //se false devo creaare activation schedule
    private ValidityPeriod validityPeriod;

    @ManyToOne
    @JoinColumn(name = "order_owner")
    @JsonBackReference(value = "boia")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "servicePKG")
    private ServicePKG servicePKG;

    @ManyToMany
    @JoinTable(name = "order_opt_product")
    private List<OptionalProduct> optionalProducts;
    //potrei mettere qua l'activation schedule così la ho linkata all'ordine

}