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
public class RevenuePerPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePKG servicePackage;

    @Column(columnDefinition = "integer default 0")
    private double revenueWithOptional;

    @Column(columnDefinition = "integer default 0")
    private double revenueWithoutOptional;
}