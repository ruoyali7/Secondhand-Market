package jhu.project.market.SecondhandMarket.Service;

import jhu.project.market.SecondhandMarket.Entity.Order;
import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }
    // Method to retrieve all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Method to retrieve an order by ID
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public boolean createOrder(int userId, int productId, int quantity) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
            Order order = new Order();
            order.setUser(user);
            order.setProduct(product);
            order.setCount(quantity);
            order.setOrderTime(LocalDateTime.now());
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            // Log the exception (optional)
            // logger.error("Error creating order", e);
            return false;
        }
    }
}
