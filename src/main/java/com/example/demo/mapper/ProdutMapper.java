package com.example.demo.mapper;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProdutMapper {

public static Product mapProduct(ProductRequest product, Category category){
  Product productmapp = new Product();
  productmapp.setName(product.getName());
  productmapp.setPriceFinal(product.getPriceFinal());
  productmapp.setDescription(product.getDescription());
  productmapp.setCategory(category);
  productmapp.setImageUrl(product.getImageUrl());
  return productmapp;

}
}
