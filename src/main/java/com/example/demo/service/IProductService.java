package com.example.demo.service;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.ProductsResponse;
import java.util.List;

public interface IProductService {

  ProductResponse createProduct(ProductRequest productRequest);
  List<ProductsResponse> allProducts();

}
