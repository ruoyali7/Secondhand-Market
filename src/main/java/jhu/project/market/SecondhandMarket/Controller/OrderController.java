package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.Order;
import jhu.project.market.SecondhandMarket.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/byId")
    public Order getOrderById(@RequestParam Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public boolean createOrder(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        return orderService.createOrder(userId, productId, quantity);
    }

}
