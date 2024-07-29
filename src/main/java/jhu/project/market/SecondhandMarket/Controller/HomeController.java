package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
HomeController for user signup, login and seller signup, login
 */
@Controller
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/login")
    public String showLogin(@RequestParam String userName, @RequestParam String password, Model model) {
        User user = userService.getUserByUsername(userName);
        String message = "";

        if (user != null && user.getPassword().equals(password)) {
            return "browsing";
        }

        if (user == null) {
            message = "Did not find user name: " + userName;
            model.addAttribute("message", message);
            logger.error("Did not find user name: {}", userName);
        }
        else {
            message = "Invalid password for username: " + userName;
            logger.error("Invalid password for username: {}", userName);
        }

        model.addAttribute("message", message);

        return "login";
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute User user, Model model) {
        String message = "";

        // Check if username exists
        if (userService.existsByUsername(user.getUsername())) {
            message = "Username already exists: " + user.getUsername();
            model.addAttribute("message", message);
            return "signup";
        }

        // Check if email exists
        if (userService.existsByEmail(user.getEmail())) {
            message = "Email already exists: " + user.getEmail();
            model.addAttribute("message", message);
            return "signup";
        }

        // If both checks pass, save the user
        userService.saveUser(user);

        if (user.isSeller()) {
            return "redirect:seller";
        }
        return "redirect:/browsing";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute User user, Model model) {
        String message = "";
        if (userService.existsByUsername(user.getUsername())) {
            message = "Username already exists: " + user.getUsername();
            model.addAttribute("message", message);
            return "login";
        }
        if (userService.existsByEmail(user.getEmail())) {
            message = "Email already exists: " + user.getEmail();
            model.addAttribute("message", message);
            return "login";
        }

        if (user.isSeller()) {
            return "redirect:seller";
        }
        return "redirect:/browsing";
    }



}
