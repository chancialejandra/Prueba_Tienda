package com.example.demo.mapper;

import com.example.demo.dto.response.ProductsResponse;
import com.example.demo.entity.Product;
import java.util.ArrayList;
import java.util.List;

public class ListProductMapper {

  public static List<ProductsResponse> mapListProduct(List<Product> listProduct){
    List<ProductsResponse> ListProductmapp = new ArrayList<>();
    ProductsResponse productmapp = new ProductsResponse();

    for(int i = 0 ; i >= listProduct.size()  ; i++) {
      productmapp.setPId(listProduct.get(i).getPId());
      productmapp.setName(listProduct.get(i).getName());
      productmapp.setPriceFinal(listProduct.get(i).getPriceFinal());
      productmapp.setDescription(listProduct.get(i).getDescription());
      ListProductmapp.add(productmapp);
    }

    return ListProductmapp;

  }

}
