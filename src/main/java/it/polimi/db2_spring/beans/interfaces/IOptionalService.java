package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.exceptions.CreationException;

public interface IOptionalService {
    OptionalProduct create(OptionalProduct optionalProduct);
}
