package dev.krupakar.productcart.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Cart {
    private Long id;
    private Long userId;
    private String date;
    private List<ProductList> products;
}
