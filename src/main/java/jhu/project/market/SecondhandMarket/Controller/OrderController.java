package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.Order;
import jhu.project.market.SecondhandMarket.Entity.OrderItem;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Service.OrderService;
import jhu.project.market.SecondhandMarket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<Order> listCartItemsForUser(@RequestParam int user_id) {
        User user = userService.getUserById(user_id);
        return orderService.listForUser(user);
    }

    @GetMapping("/listItem")
    public List<OrderItem> listOrderItems(@RequestParam int order_id) {
        return orderService.listOrderItems(order_id);
    }
}
