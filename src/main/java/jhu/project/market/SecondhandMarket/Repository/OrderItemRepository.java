package jhu.project.market.SecondhandMarket.Repository;

import jhu.project.market.SecondhandMarket.Entity.Order;
import jhu.project.market.SecondhandMarket.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findAllByOrder(Order order);
}
