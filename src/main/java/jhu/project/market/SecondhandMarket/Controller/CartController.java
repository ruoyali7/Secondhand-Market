package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.CartItem;
import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Service.CartService;
import jhu.project.market.SecondhandMarket.Service.ProductService;
import jhu.project.market.SecondhandMarket.Service.UserService;
import jhu.project.market.SecondhandMarket.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import java.util.List;

//@Controller
//@RequestMapping(path = "/cart")
//public class CartController {
//    private final CartService cartService;
//    private final ProductService productService;
//    private final UserService userService;
//
//    @Autowired
//    public CartController(CartService cartService, ProductService productService, UserService userService) {
//        this.cartService = cartService;
//        this.productService = productService;
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/list")
//    public List<CartItem> listCartItemsForUser(@RequestParam int user_id) {
//        User user = userService.getUserById(user_id);
//        return cartService.listCartItemsForUser(user);
//    }
//
//    @PostMapping("/add")
//    public boolean addCartItem(@RequestParam int user_id, @RequestParam int product_it, @RequestParam Integer count) {
//        User user = userService.getUserById(user_id);
//        Product product = productService.getProductById(product_it);
//        return cartService.addCartItm(user, product, count);
//    }
//
//    @PostMapping("/delete")
//    public boolean deleteCartItem(
//            @RequestParam int user_id,
//            @RequestParam int product_it,
//            @RequestParam(value = "count", required = false) Integer count) {
//        User user = userService.getUserById(user_id);
//        Product product = productService.getProductById(product_it);
//        if (count == null) {
//            return cartService.deleteCartItem(user, product);
//        }
//        return cartService.removeCartItem(user, product, count);
//    }
//
//    @PostMapping("/checkOut")
//    public boolean checkOutForUser(
//            @RequestParam int user_id) {
//        User user = userService.getUserById(user_id);
//        return cartService.checkOutForUser(user);
//    }
//}



@Controller
@RequestMapping(path = "/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, UserService userService, EmailService emailService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/list")
    public String listCartItemsForUser(@RequestParam int user_id, Model model, HttpSession session) {
        User user = userService.getUserById(user_id);
        if (user == null) {
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }
        List<CartItem> cartItems = cartService.listCartItemsForUser(user);
        
        session.setAttribute("cartSize", cartItems.size());
        model.addAttribute("cartItems", cartItems);
        return "cart-item";
    }

    @PostMapping("/add")
    public String addCartItem(@RequestParam int user_id, @RequestParam int product_id, @RequestParam Integer count, HttpSession session) {
        User user = userService.getUserById(user_id);
        if (user == null) {
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }
        Product product = productService.getProductById(product_id);
        System.out.println("Adding item to cart:");
        System.out.println("User ID: " + user_id);
        System.out.println("Product ID: " + product_id);
        System.out.println("Count: " + count);
        System.out.println("User Retrieved: " + user);
        System.out.println("Product Retrieved: " + product);

        boolean isAdded = cartService.addCartItm(user, product, count);

        System.out.println("Was item added? " + isAdded);      
        updateCartSizeInSession(user, session);
//        return "redirect:/cart/list?user_id=" + user_id;
        return "redirect:/product/browsing";
    }

    @PostMapping("/delete")
    public String deleteCartItem(@RequestParam int user_id, @RequestParam int product_it, @RequestParam(value = "count", required = false) Integer count, HttpSession session) {
        User user = userService.getUserById(user_id);
        if (user == null) {
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }
        Product product = productService.getProductById(product_it);
        if (count == null) {
            cartService.deleteCartItem(user, product);
        } else {
            cartService.removeCartItem(user, product, count);
        }
        updateCartSizeInSession(user, session);
        return "redirect:/cart/list?user_id=" + user_id;
    }

//    @PostMapping("/checkOut")
//    public String checkOutForUser(@RequestParam int user_id, HttpSession session) {
//        User user = userService.getUserById(user_id);
//        boolean success = cartService.checkOutForUser(user);
//        session.setAttribute("cartSize", 0); // Reset cart size after checkout
//        return "redirect:/cart/list?user_id=" + user_id;
//    }
    @PostMapping("/checkOut")
    public String showCheckoutPage(@RequestParam int user_id, Model model, HttpSession session) {
        User user = userService.getUserById(user_id);
        if (user == null) {
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }
        List<CartItem> cartItems = cartService.listCartItemsForUser(user);

        double totalPrice = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("user", user);

        return "checkout";
    }
    
    @PostMapping("/completeCheckout")
    public String completeCheckout(@RequestParam int user_id, HttpSession session, @RequestParam String cardName, @RequestParam String cardNumber, @RequestParam String expDate, @RequestParam String cvv) {
        User user = userService.getUserById(user_id);
        if (user == null) {
            return "redirect:/login"; // Redirect to login page if no user is found
        }
        List<CartItem> cartItems = cartService.listCartItemsForUser(user);
        double totalPrice = cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
        
        // Store the cart items and total price in the session for confirmation
        session.setAttribute("cartItems", cartItems);
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("user", user);

        boolean success = cartService.checkOutForUser(user);
        if (success) {
            session.setAttribute("cartSize", 0); // Reset cart size after successful checkout
            String subject = "Order Confirmation - Secondhand Market";
            String body = "Dear " + user.getFirstName() + " " + user.getLastName() + ",\n\n" +
                          "Thank you for your purchase! Your order has been successfully placed.\n\n" +
                          "Order Total: $" + session.getAttribute("totalPrice") + "\n\n" +
                          "We hope to serve you again soon!\n\n" +
                          "Best regards,\n" +
                          "Secondhand Market Team";
            emailService.sendOrderConfirmationEmail(user.getEmail(), subject, body);
            return "redirect:/cart/confirm"; // Redirect to confirmation page after successful checkout
        } else {
            return "redirect:/cart/list?user_id=" + user_id; // In case of failure, redirect back to the cart
        }
    }
    
    @GetMapping("/confirm")
    public String confirmPage(Model model, HttpSession session) {
    	User user = (User) session.getAttribute("user");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        System.out.println("here");
        System.out.println(cartItems);
        double totalPrice = (double) session.getAttribute("totalPrice");
        System.out.println(totalPrice);

        if (user == null) {
            return "redirect:/login"; // Redirect to login page if no user or cartItems are found
        }

        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "confirm";
    }


    private void updateCartSizeInSession(User user, HttpSession session) {
        List<CartItem> cartItems = cartService.listCartItemsForUser(user);
        session.setAttribute("cartSize", cartItems.size());
    }
}
