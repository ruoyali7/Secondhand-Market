package jhu.project.market.SecondhandMarket.Repository;

import jhu.project.market.SecondhandMarket.Entity.CartItem;
import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findCartItemByUserAndProduct(User user, Product product);
    List<CartItem> findCartItemsByUser(User user);
}
