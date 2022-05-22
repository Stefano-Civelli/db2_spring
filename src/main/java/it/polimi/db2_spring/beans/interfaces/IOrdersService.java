package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.exceptions.CreationException;

import java.util.List;

public interface IOrdersService {
   Orders create(Orders order) throws CreationException;
   List<Orders> getOrdersList(Users user);
   List<Orders> getRejectedOrderList(Users user);
   Orders getById(Long id) throws Exception;
   Orders update(Orders order);
}
