package it.polimi.db2_spring.entities.materializedViews;

import it.polimi.db2_spring.entities.ServicePKG;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasesPerPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private ServicePKG servicePackage;

    @Column(columnDefinition = "integer default 0")
    private int numberOfPurchases;
}