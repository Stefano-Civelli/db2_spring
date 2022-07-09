package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CreationException;
import it.polimi.db2_spring.utility.supportForQueries.IActivationSchedule;
import it.polimi.db2_spring.utility.supportForQueries.IOrders;

import java.util.List;

public interface IOrdersService {
   Orders create(Orders order) throws CreationException;
   List<Orders> getOrdersList(Users user);
   List<IActivationSchedule> getOrdersActivationSchedule(Users user);
   List<IOrders> getRejectedOrderList(Users user);
   Orders getById(Long id) throws Exception;
   Orders update(Orders order);
}
