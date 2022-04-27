package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidityPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int months;
    private float monthlyFee;

    @JsonIgnore
    @ManyToMany(mappedBy = "periods")
    private List<ServicePKG> servicePKGs;

    @JsonIgnore
    @OneToMany(mappedBy = "period")
    private List<Orders> orders;
}
