package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    
    @GetMapping("/browsing")
    public String showBrowsingPage(@RequestParam(required = false) String category,
                                   @RequestParam(required = false) String search,
                                   HttpSession session,
                                   Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }

        List<Product> products;
        if (category != null && !category.isEmpty()) {
            products = productService.getAllProductsByCategory(category);
        } else if (search != null && !search.isEmpty()) {
            products = productService.getAllProducts().stream()
                    .filter(product -> product.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            products = productService.getAllProducts();
        }

        model.addAttribute("products", products);
        model.addAttribute("isSeller", user.isSeller());

        return "browsing";  
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/byCategory")
    public List<Product> getAllProductsByCategory(@RequestParam String category) {
        return productService.getAllProductsByCategory(category);
    }

    @GetMapping("/byId")
    @ResponseBody
    public Product getProductById(@RequestParam int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
}