package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @Column(nullable = false)
    private Date creationTime;
    @Column(nullable = false)
    private double totalValue;
    @Column(nullable = false)
    private double packageValueWithoutOptions;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date startingDateOfSubscription;
    private Boolean isRejected; //se false devo creaare activation schedule

    @ManyToOne(fetch = FetchType.EAGER,
//            cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//            CascadeType.REFRESH,
//            CascadeType.DETACH},
            optional = false
    )
    @JoinColumn(name = "order_owner")
    @JsonBackReference(value = "boia")
    private Users user;

    @ManyToOne (
//            cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE,
//            CascadeType.REFRESH,
//            CascadeType.DETACH},
            optional = false
    )
    @JoinColumn(name = "servicePKG")
    private ServicePKG servicePKG;

    @ManyToMany
    @JoinTable(name = "order_opt_product")
    private List<OptionalProduct> optionalProducts;

    @ManyToOne
    @JoinColumn(name = "period_id")
    private ValidityPeriod period;
}