package jhu.project.market.SecondhandMarket.Repository;

import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getProductsBySeller(User seller);

    List<Product> getProductsByCategory(String category);
}