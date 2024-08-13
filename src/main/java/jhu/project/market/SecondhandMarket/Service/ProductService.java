package jhu.project.market.SecondhandMarket.Service;

import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
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

    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.getProductsByCategory(category);
    }
}
