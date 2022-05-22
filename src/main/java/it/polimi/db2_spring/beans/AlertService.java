package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IAlertService;
import it.polimi.db2_spring.entities.Alert;
import it.polimi.db2_spring.repo.AlertRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AlertService implements IAlertService {

   private final AlertRepo alertRepo;

   @Override
   public Alert create(Alert alert) {
      log.info("saving new alert " + alert.getAlertId() + " in the DB");
      return alertRepo.save(alert);
   }
}
