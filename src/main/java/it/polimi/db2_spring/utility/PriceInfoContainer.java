package it.polimi.db2_spring.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
public class PriceInfoContainer {
    private long period;
    private List<Long> selectedOptionalProducts;
}
