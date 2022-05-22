package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IOptionalService;
import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.repo.OptionalProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OptionalService implements IOptionalService {

    private final OptionalProductRepo optionalProductRepo;

    @Override
    public OptionalProduct create(OptionalProduct optionalProduct) {
            log.info("saving new optional product " + optionalProduct.getName() + " in the DB");
            return optionalProductRepo.save(optionalProduct);
    }
}
