package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IOrdersService;
import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrdersService implements IOrdersService {

   private final OrderRepo orderRepo;


   @Override
   public Orders create(Orders order) {
      log.info("saving new order " + order.getId() + " in the DB");
      return orderRepo.save(order);
   }

   @Override
   public List<Orders> getOrdersList(Users user) {
      log.info("fetching orders from user: " + user.getUsername());
      return orderRepo.findAllByUser(user);
   }

   @Override
   public List<Orders> getRejectedOrderList(Users user) {
      log.info("fetching rejected orders from user: " + user.getUsername());
      return orderRepo.findAllByUser(user).stream().filter(x -> x.getIsRejected().equals(TRUE)).collect(Collectors.toList());
   }

}
