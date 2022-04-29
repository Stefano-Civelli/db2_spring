package it.polimi.db2_spring.entities.materializedViews;

import it.polimi.db2_spring.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsolventUsers {
    // Trick to have foreign key also be the primary key of this table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "id")
    private Users user;
}
