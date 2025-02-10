package com.se.nobsexam.dto;

import com.se.nobsexam.model.Category;
import com.se.nobsexam.model.Product;
import lombok.Getter;

@Getter
public class ProductDto {
    private String name;
    private String description;
    private Double price;
    private String manufacturer;
    private String category;


    public ProductDto(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.manufacturer = product.getManufacturer();
        this.category=toString(product.getCategory());

    }

    private String toString(Category category) {
        return category.getName();
    }
}
