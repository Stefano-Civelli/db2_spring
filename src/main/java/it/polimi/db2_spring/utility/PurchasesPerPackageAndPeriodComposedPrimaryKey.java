package it.polimi.db2_spring.utility;

import it.polimi.db2_spring.entities.ServicePKG;
import it.polimi.db2_spring.entities.ValidityPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PurchasesPerPackageAndPeriodComposedPrimaryKey implements Serializable {

    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePKG servicePackage;

    @OneToOne
    @JoinColumn(name = "period_id")
    private ValidityPeriod validityPeriod;

}
