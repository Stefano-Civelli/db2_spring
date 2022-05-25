package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IServicesService;
import it.polimi.db2_spring.entities.Services;
import it.polimi.db2_spring.repo.ServiceRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ServicesService implements IServicesService {

    private final ServiceRepo serviceRepo;

    @Override
    public List<Services> getServicesList(int limit) {
        log.info("fetching Service Package list");
        if(limit == 0)
            return serviceRepo.findAll();
        return serviceRepo.findAll().stream().limit(limit).collect(Collectors.toList());
    }
}
