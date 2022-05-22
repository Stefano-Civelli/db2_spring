package it.polimi.db2_spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false)
    private int months;
    @Column(nullable = false)
    private float monthlyFee;

    @JsonIgnore
    @ManyToMany(mappedBy = "periods", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<ServicePKG> servicePKGs;

    @JsonIgnore
    @OneToMany(mappedBy = "period", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Orders> orders;
}
