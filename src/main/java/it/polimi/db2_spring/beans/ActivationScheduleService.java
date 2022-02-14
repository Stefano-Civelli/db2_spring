package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IActivationScheduleService;
import it.polimi.db2_spring.entities.ActivationSchedule;
import it.polimi.db2_spring.repo.ActivationScheduleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ActivationScheduleService implements IActivationScheduleService {

    private final ActivationScheduleRepo activationScheduleRepo;

    @Override
    public ActivationSchedule create(ActivationSchedule schedule) {
        log.info("saving new schedule " + schedule.getId() + " in the DB");
        return activationScheduleRepo.save(schedule);
    }
}
