package jhu.project.market.SecondhandMarket.Service;

import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllByCountGreaterThan(0);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProductsBySeller(User seller) {
        return productRepository.getProductsBySeller(seller);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.getProductsByCategoryAndCountIsGreaterThan(category, 0);
    }

    // Step 1: Checks whether the product table (a.k.a inventory) contains enough count for each
    // product. Returns false if not.
    // Step 2: Delete corresponding item count from product table.
    public boolean checkAvailabilityAndRemoveItems(List<Product> products) {
        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
        List<Product> inventory = productRepository.findAllById(productMap.keySet());
        if (inventory.size() != productMap.size()) {
            return false;
        }
        for (Product product: inventory) {
            Product cartItem = productMap.get(product.getId());
            if (cartItem == null || cartItem.getCount() > product.getCount()) {
                return false;
            }
            product.deleteCount(cartItem.getCount());
        }
        productRepository.saveAll(products);
        return true;
    }
}
