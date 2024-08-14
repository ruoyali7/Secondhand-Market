package jhu.project.market.SecondhandMarket.Service;

import jhu.project.market.SecondhandMarket.Entity.CartItem;
import jhu.project.market.SecondhandMarket.Entity.Product;
import jhu.project.market.SecondhandMarket.Entity.User;
import jhu.project.market.SecondhandMarket.Repository.CartItemRepository;
import jhu.project.market.SecondhandMarket.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    @Autowired
    public CartService(CartItemRepository cartItemRepository, OrderService orderService) {
        this.cartItemRepository = cartItemRepository;
        this.orderService = orderService;
    }

    public List<CartItem> listCartItemsForUser(User user) {
        return cartItemRepository.findCartItemsByUser(user);
    }

    public boolean addCartItm(User user, Product product, Integer count) {
        try {
        	System.out.println("Attempting to add item to cart:");
            System.out.println("User: " + user);
            System.out.println("Product: " + product);
            System.out.println("Count: " + count);
            CartItem cartItem = cartItemRepository.findCartItemByUserAndProduct(user, product).orElse(null);
            if (cartItem == null) {
            	System.out.println("Cart item not found, creating new cart item.");
                cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
            }
            cartItem.addItems(count);
            cartItemRepository.save(cartItem);
            System.out.println("Cart item saved successfully.");
            return true;
        } catch (Exception e) {
        	System.out.println("Error adding item to cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCartItem(User user, Product product) {
        return removeCartItem(user, product, 1); //set this to 1 to lower count by 1
    }

    public boolean removeCartItem(User user, Product product, Integer count) {
        try {
            CartItem cartItem = cartItemRepository.findCartItemByUserAndProduct(user, product)
                                                   .orElseThrow(() -> new RuntimeException("Cart item not found"));
            cartItem.deleteItems(count);

            if (cartItem.getCount() == 0) {
                cartItemRepository.delete(cartItem);
            } else {
                cartItemRepository.save(cartItem);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkOutForUser(User user) {
        List<CartItem> cartItems = cartItemRepository.findCartItemsByUser(user);
        if (cartItems.isEmpty()) {
            return false;
        }
        orderService.createOrder(user, cartItems);
        cartItemRepository.deleteAll(cartItems);
        return true;
    }
}
