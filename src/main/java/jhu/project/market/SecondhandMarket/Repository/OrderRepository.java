package jhu.project.market.SecondhandMarket.Repository;

import jhu.project.market.SecondhandMarket.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
