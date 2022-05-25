package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.OptionalProduct;

import java.util.List;

public interface IOptionalService {
    OptionalProduct create(OptionalProduct optionalProduct);
    List<OptionalProduct> getOptionalList(int limit);
}
