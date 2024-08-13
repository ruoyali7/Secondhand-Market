package jhu.project.market.SecondhandMarket.Service;

import jhu.project.market.SecondhandMarket.Entity.*;
import jhu.project.market.SecondhandMarket.Repository.OrderItemRepository;
import jhu.project.market.SecondhandMarket.Repository.OrderRepository;
import jhu.project.market.SecondhandMarket.Repository.ProductRepository;
import jhu.project.market.SecondhandMarket.Repository.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> listForUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public List<OrderItem> listOrderItems(int order_id) {
        try {
            Order order = orderRepository.findById(order_id).orElseThrow(() -> new RuntimeException("Order not found"));
            return orderItemRepository.findAllByOrder(order);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean createOrder(User user, List<CartItem> cartItems) {
        Order order = new Order();
        order.setUser(user);
        order.setItemCount(cartItems.size());
        order.setTotalPrice(cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum());
        Order savedOrder = orderRepository.save(order);
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setCount(cartItem.getCount());
            orderItem.setPrice(cartItem.getTotalPrice());
            return orderItem;
        }).toList();
        orderItemRepository.saveAll(orderItems);
        return true;
    }
}
