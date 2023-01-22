package com.example.demo.mapper;

import com.example.demo.dto.response.ProductsResponse;
import com.example.demo.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {

  List<ProductsResponse> mapProducts(List<Product> products);
}
