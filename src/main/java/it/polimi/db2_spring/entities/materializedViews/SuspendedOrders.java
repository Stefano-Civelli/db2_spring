package it.polimi.db2_spring.entities.materializedViews;

import it.polimi.db2_spring.entities.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuspendedOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders order;
}
