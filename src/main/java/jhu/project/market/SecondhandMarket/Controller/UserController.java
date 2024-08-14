package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Entity.Order;
import jhu.project.market.SecondhandMarket.Service.UserService;
import jhu.project.market.SecondhandMarket.Service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/byId")
    public User getUserById(@RequestParam int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username")
    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @GetMapping("/profile")
    public String showUserProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // Redirect to login if the user is not logged in
        }

        List<Order> orders = orderService.listForUser(user);

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);

        return "userProfile"; // This is the JSP page for the user profile
    }

}
