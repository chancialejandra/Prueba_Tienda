package com.example.demo.mapper;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public static Product mapProduct(ProductRequest product, Category category) {
    Product productMapp = new Product();
    productMapp.setName(product.getName());
    productMapp.setPriceFinal(product.getPriceFinal());
    productMapp.setDescription(product.getDescription());
    productMapp.setCategory(category);
    productMapp.setImageUrl(product.getImageUrl());
    return productMapp;

    //Pasar a Builder
  }
}
