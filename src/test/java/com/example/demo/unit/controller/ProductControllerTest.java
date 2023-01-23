package com.example.demo.unit.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.ProductsResponse;
import com.example.demo.service.IProductService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {


  @InjectMocks
  ProductController productController;

  @Mock
  IProductService productService;

  @Nested
  class whenSearchProducts {

    @Test
    public void shouldReturnAllProductsWithStatusOk_WhenExists() {

      //Given
      List<ProductsResponse> products = buildProducts();

      //When
      when(productService.allProducts()).thenReturn(products);

      var response = productController.allProducts();

      //Then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void shouldReturnStatusNoContent_WhenNotExistsProducts() {

      //When
      when(productService.allProducts()).thenReturn(null);

      var response = productController.allProducts();

      //Then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


  }

  @Nested
  class whenCreateProducts {
    @Test
    public void shouldReturnStatusOk_WhenCreateProductSuccessfully() {
      //Given
      ProductResponse product = ProductResponse.builder()
          .pId(1)
          .status(HttpStatus.OK)
          .message("")
          .build();

      //When
      when(productService.createProduct(any())).thenReturn(product);
      var response = productController.createProduct(any());

      //Then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
  }

  // TODO: Validar los diferentes casos y servicios de productos


  private List<ProductsResponse> buildProducts() {

    ProductsResponse product1 = ProductsResponse.builder()
        .pId(1)
        .name("Leche Colanta")
        .priceFinal(5000)
        .description("Leche Entera")
        .build();
    ProductsResponse product2 = ProductsResponse.builder()
        .pId(2)
        .name("Arbeja")
        .priceFinal(2000)
        .description("Arbeja Desvainada")
        .build();

    List<ProductsResponse> products = new ArrayList<>();

    products.add(product1);
    products.add(product2);

    return products;
  }
}
