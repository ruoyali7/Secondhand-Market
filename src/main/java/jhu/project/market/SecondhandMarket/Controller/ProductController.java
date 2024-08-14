package jhu.project.market.SecondhandMarket.Controller;

import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                                   Model model) {
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
        return "browsing";  // This will return the browsing.jsp view
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