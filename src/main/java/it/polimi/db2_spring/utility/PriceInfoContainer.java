package it.polimi.db2_spring.utility;

import it.polimi.db2_spring.entities.OptionalProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PriceInfoContainer {
    private long period;
    private List<Long> selectedOptionalProducts;
}
