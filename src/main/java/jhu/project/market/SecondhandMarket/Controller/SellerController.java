package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Service.ProductService;
import jhu.project.market.SecondhandMarket.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping(path = "/seller")
public class SellerController {
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public SellerController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }
    @GetMapping("/dashboard")
    public String showSellerDashboard(HttpSession session, Model model) {
        User seller = (User) session.getAttribute("user");

        if (seller == null || !seller.isSeller()) {
            return "redirect:/login"; // Redirect to login if the user is not logged in or not a seller
        }

        List<Product> products = productService.getAllProductsBySeller(seller);
        model.addAttribute("products", products);

        return "seller"; // Return the seller.jsp view
    }


    @GetMapping("/list")
    public List<Product> getAllProductsForSeller(@RequestParam int id) {
        User seller = userService.getUserById(id);
        return productService.getAllProductsBySeller(seller);
    }

    @PostMapping("/create")
    @ResponseBody
    public String createProduct(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam double price,
            @RequestParam int count) {
        User seller = userService.getUserById(id);
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setSeller(seller);
        product.setCategory(category);
        product.setCount(count);
        productService.saveProduct(product);
        return "success"; 
    }


    @PostMapping("/update")
    public String updateProduct(
            @RequestParam int productId,
            @RequestParam int count,
            HttpSession session) {

        Product product = productService.getProductById(productId);
        product.setCount(count);
        productService.saveProduct(product);

        return "redirect:/seller/dashboard";
    }


}
