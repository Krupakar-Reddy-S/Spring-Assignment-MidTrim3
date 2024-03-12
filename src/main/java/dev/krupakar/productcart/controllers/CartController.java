package dev.krupakar.productcart.controllers;

import java.util.List;

import dev.krupakar.productcart.models.Cart;
import dev.krupakar.productcart.services.CartServiceImpl;

import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    private CartServiceImpl CartService;

    public CartController(CartServiceImpl cartService) {
        this.CartService = cartService;
    }

    private final String HomePageHTML = """ 
            <h1>Welcome to the Cart Service</h1>
            <h3>Use the following endpoints to interact with the Cart Service</h3>
            <ul>
                <li>GET/carts - to get all carts</li>
                <li>GET /carts/{id} - to get cart by id</li>
                <li>GET /carts?startData=yyyy-MM-dd&endDate=yyyy-MM-dd - to get all carts int the given range</li>
                <li>GET /carts/user/{userId} - to get all carts of given userId</li>
                <li>POST /carts - to create a new cart of given id</li>
                <li>PUT /carts/{id} - to update the cart of given id</li>
                <li>DELETE /carts/{id} - to delete the cart of given id</li>
            </ul>
            """;

    @GetMapping("/")
    public String HomePage() {
        return HomePageHTML;
    }

    // An endpoint to get all carts
    @GetMapping("/carts")
    public List<Cart> getAllCarts() {
        return CartService.getAllCarts();
    }

    // An endpoint to get cart by id
    @GetMapping("/carts/{id}")
    public Cart getCart(@PathVariable Long id) {
        return CartService.getCart(id);
    }

    // An endpoint at carts with queries startData and endDate, to get all carts int the given range
    // carts?startdate=2019-12-10&enddate=2020-10-10
    @RequestMapping("/carts")
    public List<Cart> getCartsByDateRange(@RequestParam String startdate, @RequestParam String enddate) {
        return CartService.getCartsByDateRange(startdate, enddate);
    }

    // An endpoint to get all carts of given userId
    @GetMapping("/carts/user/{userId}")
    public List<Cart> getUserCart(@PathVariable Long userId) {
        return CartService.getUserCart(userId);
    }

    // An endpoint to create a new cart of given id
    @PostMapping("/carts")
    public Cart addCart(@RequestBody Cart cart) {
        return CartService.addCart(cart);
    }

    // An endpoint to update the cart of given id
    @PutMapping("/carts/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        return CartService.updateCart(id, cart);
    }

    // An endpoint to delete the cart of given id
    @DeleteMapping("/carts/{id}")
    public String deleteCart(@PathVariable Long id) {
        CartService.deleteCart(id);
        return "Cart: " + id + " deleted successfully";
    }
}
