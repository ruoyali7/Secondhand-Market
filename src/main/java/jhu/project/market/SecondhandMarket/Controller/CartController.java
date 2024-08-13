package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.CartItem;
import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Service.CartService;
import jhu.project.market.SecondhandMarket.Service.ProductService;
import jhu.project.market.SecondhandMarket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductService productService, UserService userService) {
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }


    @GetMapping("/list")
    public List<CartItem> listCartItemsForUser(@RequestParam int user_id) {
        User user = userService.getUserById(user_id);
        return cartService.listCartItemsForUser(user);
    }

    @PostMapping("/add")
    public boolean addCartItem(@RequestParam int user_id, @RequestParam int product_it, @RequestParam Integer count) {
        User user = userService.getUserById(user_id);
        Product product = productService.getProductById(product_it);
        return cartService.addCartItm(user, product, count);
    }

    @PostMapping("/delete")
    public boolean deleteCartItem(
            @RequestParam int user_id,
            @RequestParam int product_it,
            @RequestParam(value = "count", required = false) Integer count) {
        User user = userService.getUserById(user_id);
        Product product = productService.getProductById(product_it);
        if (count == null) {
            return cartService.deleteCartItem(user, product);
        }
        return cartService.removeCartItem(user, product, count);
    }

    @PostMapping("/checkOut")
    public boolean checkOutForUser(
            @RequestParam int user_id) {
        User user = userService.getUserById(user_id);
        return cartService.checkOutForUser(user);
    }
}
