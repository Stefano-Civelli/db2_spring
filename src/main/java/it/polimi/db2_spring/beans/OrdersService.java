package it.polimi.db2_spring.beans;

import it.polimi.db2_spring.beans.interfaces.IOrdersService;
import it.polimi.db2_spring.entities.OptionalProduct;
import it.polimi.db2_spring.entities.Orders;
import it.polimi.db2_spring.entities.Users;
import it.polimi.db2_spring.entities.ValidityPeriod;
import it.polimi.db2_spring.exceptions.CredentialsException;
import it.polimi.db2_spring.exceptions.CreationException;
import it.polimi.db2_spring.repo.OptionalProductRepo;
import it.polimi.db2_spring.repo.OrderRepo;
import it.polimi.db2_spring.repo.ValidityPeriodRepo;
import it.polimi.db2_spring.utility.supportForQueries.IOrders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrdersService implements IOrdersService {

   private final OrderRepo orderRepo;
   private final ValidityPeriodRepo validityPeriodRepo;
   private final OptionalProductRepo optionalProductRepo;

   @Override
   public Orders create(Orders order) throws CreationException {
      log.info("saving new order " + order.getId() + " in the DB");

      List<OptionalProduct> optionalProducts = order.getOptionalProducts();
      OptionalProduct optionalProduct = null;
      int optionalProductMonthlyRevenue = 0;

      if(optionalProducts != null) {
         for (OptionalProduct product : optionalProducts) {

            Optional<OptionalProduct> optionalFromDb = optionalProductRepo.findById(product.getProductCode());

            if(optionalFromDb.isEmpty())
               throw new CreationException("One of the specified optional products does not exists");

            optionalProduct = optionalFromDb.get();
            optionalProductMonthlyRevenue += optionalProduct.getMonthlyFee();
         }
      }

      Optional<ValidityPeriod> periodFromDb = validityPeriodRepo.findById(order.getPeriod().getId());

      if(periodFromDb.isEmpty())
         throw new CreationException("The specified validity period does not exists");

      ValidityPeriod period = periodFromDb.get();

      order.setTotalValue(period.getMonths() * (period.getMonthlyFee() + optionalProductMonthlyRevenue));
      order.setPackageValueWithoutOptions(period.getMonths() * period.getMonthlyFee());
      order.setCreationTime(new Timestamp(new Date().getTime()));
      return orderRepo.save(order);
   }

   @Override
   public List<Orders> getOrdersList(Users user) {
      log.info("fetching orders from user: " + user.getUsername());
      return orderRepo.findAllByUser(user);
   }

   @Override
   public List<IOrders> getRejectedOrderList(Users user) {
      log.info("fetching rejected orders from user: " + user.getUsername());
      //return orderRepo.findAllByUser(user).stream().filter(x -> x.getIsRejected().equals(TRUE)).collect(Collectors.toList());
      return orderRepo.findRejectedOrdersOfUser(user);
   }

   @Override
   public Orders getById(Long id) throws CredentialsException {
      Optional<Orders> orderDb = orderRepo.findById(id);

      log.info("getting from Db order: " + id);

      if(orderDb.isEmpty())
         throw new CredentialsException("Order you tried to retrieve is not present in the DB");

      return orderDb.get();
   }

   @Override
   public Orders update(Orders order) {
      log.info("updating order " + order.getId() + " in the DB");
      return orderRepo.save(order);
   }
}
