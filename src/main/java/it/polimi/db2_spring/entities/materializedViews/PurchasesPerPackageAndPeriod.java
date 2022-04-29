package it.polimi.db2_spring.entities.materializedViews;

import it.polimi.db2_spring.utility.PurchasesPerPackageAndPeriodComposedPrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasesPerPackageAndPeriod {
    @EmbeddedId
    private PurchasesPerPackageAndPeriodComposedPrimaryKey key;

    @Column( columnDefinition = "integer default 0")
    private int numberOfPurchases;
}
