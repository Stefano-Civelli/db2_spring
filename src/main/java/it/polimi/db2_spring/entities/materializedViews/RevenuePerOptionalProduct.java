package it.polimi.db2_spring.entities.materializedViews;

import it.polimi.db2_spring.entities.OptionalProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenuePerOptionalProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "optional_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "optional_id")
    private OptionalProduct optionalProduct;

    @Column(columnDefinition = "integer default 0")
    private double totalRevenue;

    private Boolean isBest;
}
