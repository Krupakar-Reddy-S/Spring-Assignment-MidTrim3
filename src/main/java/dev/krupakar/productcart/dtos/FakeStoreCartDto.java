package dev.krupakar.productcart.dtos;

import java.util.List;

import dev.krupakar.productcart.models.ProductList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCartDto {
    private Long id;
    private Long userId;
    private String date;
    private List<ProductList> products;
}
