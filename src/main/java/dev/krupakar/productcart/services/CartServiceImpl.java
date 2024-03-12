package dev.krupakar.productcart.services;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import dev.krupakar.productcart.models.Cart;
import dev.krupakar.productcart.dtos.FakeStoreCartDto;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartServiceImpl implements CartService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_BASE_URL = "https://fakestoreapi.com/carts";

    public List<Cart> getAllCarts() {
        return Arrays.stream(restTemplate.getForObject(API_BASE_URL, FakeStoreCartDto[].class))
                .map(this::mapDtoToCart)
                .collect(Collectors.toList());
    }

    public Cart getCart(Long id) {
        return mapDtoToCart(restTemplate.getForObject(API_BASE_URL + "/" + id, FakeStoreCartDto.class));
    }

    public List<Cart> getCartsByDateRange(String startdate, String enddate) {
        return Arrays.stream(restTemplate.getForObject(API_BASE_URL + "?startdate=" + startdate + "&enddate=" + enddate, FakeStoreCartDto[].class))
                .map(this::mapDtoToCart)
                .collect(Collectors.toList());
    }

    public List<Cart> getUserCart(Long userId) {
        return Arrays.stream(restTemplate.getForObject(API_BASE_URL + "/user/" + userId, FakeStoreCartDto[].class))
                .map(this::mapDtoToCart)
                .collect(Collectors.toList());
    }

    public Cart addCart(Cart cart) {
        restTemplate.postForObject(API_BASE_URL, mapCartToDto(cart), FakeStoreCartDto.class);
        System.out.println("Cart created successfully!!!");
        return cart;
    }

    public Cart updateCart(Long id, Cart cart) {
        restTemplate.put(API_BASE_URL + "/" + id, mapCartToDto(cart));
        System.out.println("Cart updated successfully!!!");
        return cart;
    }

    public String deleteCart(Long id) {
        restTemplate.delete(API_BASE_URL + "/" + id);
        System.out.println("Cart deleted successfully!!!");
        return "Cart deleted successfully";
    }

    private Cart mapDtoToCart(FakeStoreCartDto dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUserId(dto.getUserId());
        cart.setDate(dto.getDate());
        cart.setProducts(dto.getProducts());

        return cart;
    }

    private FakeStoreCartDto mapCartToDto(Cart cart) {
        FakeStoreCartDto storeCart = new FakeStoreCartDto();
        storeCart.setId(cart.getId());
        storeCart.setUserId(cart.getUserId());
        storeCart.setDate(cart.getDate());
        storeCart.setProducts(cart.getProducts());

        return storeCart;
    }

}
