package com.example.demo.service;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.dto.response.ProductsResponse;
import com.example.demo.entity.Product;
import com.example.demo.mapper.IProductMapper;
import com.example.demo.mapper.ListProductMapper;
import com.example.demo.mapper.ProdutMapper;
import com.example.demo.repository.IProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{

  private final IProductRepository productRepository;
  private final ICategoryService categoryService;
//  private final IProductMapper mapperProduct;

  ModelMapper mapper = new ModelMapper();

  public ProductService(IProductRepository productRepository, ICategoryService categoryService)
//                        IProductMapper mapperProduct)
                       {
    this.productRepository = productRepository;
    this.categoryService = categoryService;
//    this.mapperProduct = mapperProduct;
  }


  @Override
  public ProductResponse product(ProductRequest productRequest) {

    ProductResponse productResponse = null;

    try {
        if(!categoryService.existCategory(productRequest.getCategoryId())){
          return ProductResponse.builder()
              .message("la categoria no existe")
             .status(HttpStatus.BAD_REQUEST)
              .build();
        }else{
          var categoryProduct = categoryService.searchCategory(productRequest.getCategoryId());
          Product product = ProdutMapper.mapProduct(productRequest,categoryProduct);

          var response = productRepository.save(product);
          productResponse = ProductResponse.builder()
              .pId(response.getPId())
              .message("Guardado con exito")
              .status(HttpStatus.OK)
              .build();

          return productResponse;
      }
    }catch(Exception ex){
      System.out.println("Error guardando");
    }
    return productResponse;
  }

  @Override
  public List<ProductsResponse> allProduct() {
    List<ProductsResponse> listProduts = productRepository.findAll()
        .stream()
        .map(x -> mapper.map(x,ProductsResponse.class))
        .collect(Collectors.toList());
   return listProduts;

  }
}
