package com.example.demo.controller;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.service.IProductService;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nexsys/v1/products/")
public class ProductController {

  private final IProductService productService;

  public ProductController(IProductService productService) {
    this.productService = productService;
  }


  @PostMapping()
  public ResponseEntity<?> createProduct(@RequestBody ProductRequest product) {
    var response = productService.createProduct(product);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping()
  public ResponseEntity<?> allProducts() {
    var products = productService.allProducts();

    if (Objects.isNull(products)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(products);
  }
}
