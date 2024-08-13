package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
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
@RequestMapping(path = "/seller")
public class SellerController {
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public SellerController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }


    @GetMapping("/list")
    public List<Product> getAllProductsForSeller(@RequestParam int id) {
        User seller = userService.getUserById(id);
        return productService.getAllProductsBySeller(seller);
    }

    @PostMapping("/create")
    public Product createProduct(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam double price) {
        User seller = userService.getUserById(id);
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setSeller(seller);
        product.setCategory(category);
        return productService.saveProduct(product);
    }

}
