package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Services;

import java.util.List;

public interface IServicesService {
    List<Services> getServicesList(int limit);
}
