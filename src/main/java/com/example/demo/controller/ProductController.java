package com.example.demo.controller;


import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductsResponse;
import com.example.demo.service.IProductService;
import java.util.List;
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
  public ResponseEntity<?> product(@RequestBody ProductRequest product){
    var response = productService.product(product);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping()
  public List<ProductsResponse> allProduct(){
    var response = productService.allProduct();
    return (List<ProductsResponse>) response;
  }
}
