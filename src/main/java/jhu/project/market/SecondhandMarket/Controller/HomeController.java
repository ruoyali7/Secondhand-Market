package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController{
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
        User user = userService.getUserByUsername(userName);
        model.addAttribute("user", user);
        if (user == null) {
            // Return some error message "User not found"
            String message = "Invalid username or password";
            model.addAttribute("message", message);
            logger.error("Did not find user name: {}", userName);
            return "login";
        }
        if (!user.getPassword().equals(password)) {
            // Return some error message "Invalid password"
            logger.error("Invalid password for user name: {}", userName);
            return "login";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

//    @GetMapping({"/", "/login"})
//    public String showHomePage(String name, Model model) {
//        name = "World";
//        model.addAttribute("name", name);
//        return "home";
//    }
}
