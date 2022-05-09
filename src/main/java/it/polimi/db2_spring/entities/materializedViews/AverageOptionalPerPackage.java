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
public class AverageOptionalPerPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "package_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePKG servicePackage;

    @Column(columnDefinition = "double default 0")
    private double averageNumberOfOptionals;
}
