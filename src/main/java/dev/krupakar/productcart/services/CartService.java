package dev.krupakar.productcart.services;

import dev.krupakar.productcart.models.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CartService {

    public List<Cart> getAllCarts();

    public Cart getCart(Long id);

    public List<Cart> getCartsByDateRange(String startDate, String endDate);

    public List<Cart> getUserCart(Long userId);

    public Cart addCart(Cart cart);

    public Cart updateCart(Long id, Cart cart);

    public String deleteCart(Long id);

}
