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
            CartItem cartItem = cartItemRepository.findCartItemByUserAndProduct(user, product).orElseThrow(() -> new RuntimeException("Cart item not found"));;
            if (cartItem == null) {
                cartItem = new CartItem();
                cartItem.setUser(user);
                cartItem.setProduct(product);
            }
            cartItem.addItems(count);
            cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteCartItem(User user, Product product) {
        return removeCartItem(user, product, Integer.MAX_VALUE);
    }

    public boolean removeCartItem(User user, Product product, Integer count) {
        try {
            CartItem cartItem = cartItemRepository.findCartItemByUserAndProduct(user, product).orElseThrow(() -> new RuntimeException("Cart item not found"));
            cartItem.deleteItems(count);
            if (cartItem.getCount() == 0) {
                cartItemRepository.delete(cartItem);
            } else {
                cartItemRepository.save(cartItem);
            }
            return true;
        } catch (Exception e) {
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
