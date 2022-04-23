package it.polimi.db2_spring.beans.interfaces;

import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;

import java.util.List;

public interface IOrdersService {
   Orders create(Orders order);
   List<Orders> getOrdersList(Users user);
   List<Orders> getRejectedOrderList(Users user);
   Orders getById(Long id) throws Exception;
   Orders update(Orders order);
}
