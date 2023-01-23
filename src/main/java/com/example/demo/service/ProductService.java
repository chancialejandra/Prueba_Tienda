package com.example.demo.service;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.ProductsResponse;
import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.IProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

  private final IProductRepository productRepository;
  private final ICategoryService categoryService;

  ModelMapper mapper = new ModelMapper();

  public ProductService(IProductRepository productRepository, ICategoryService categoryService) {
    this.productRepository = productRepository;
    this.categoryService = categoryService;
  }


  @Override
  public ProductResponse createProduct(ProductRequest productRequest) {

    try {
      if (!categoryService.existCategory(productRequest.getCategoryId())) {
        return ProductResponse.builder()
            .message("la categoria no existe")
            .status(HttpStatus.BAD_REQUEST)
            .build();
      }
      var categoryProduct = categoryService.searchCategory(productRequest.getCategoryId());
      Product product = ProductMapper.mapProduct(productRequest, categoryProduct);

      var response = productRepository.save(product);
      return ProductResponse.builder()
          .pId(response.getPId())
          .message("Guardado con exito")
          .status(HttpStatus.OK)
          .build();

    } catch (Exception ignored) {

    }
    return null;
  }

  @Override
  public List<ProductsResponse> allProducts() {
    return productRepository.findAll()
        .stream()
        .map(x -> mapper.map(x, ProductsResponse.class))
        .collect(Collectors.toList());

  }
}
