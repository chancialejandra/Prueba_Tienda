package com.example.demo.service;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.CategorysResponse;
import com.example.demo.entity.Category;
import com.example.demo.repository.ICategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{

  private final ICategoryRepository categoryRepository;
  ModelMapper mapper = new ModelMapper();

  public CategoryService(ICategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Boolean existCategory(Integer cId) {
    return categoryRepository.existsById(cId);
  }

  @Override
  public CategoryResponse category(CategoryRequest categoryRequest) {

    CategoryResponse categoryResponse = null;
    Category category = mapper.map(categoryRequest,Category.class);
    try{
      if(existCategory(category.getCId())){//revisar
        return CategoryResponse.builder()
          .message("la categoria ya existe")
          .status(HttpStatus.BAD_REQUEST)
          .build();
      }else{
        var response = categoryRepository.save(category);
        categoryResponse = CategoryResponse.builder()
          .cId(response.getCId())
          .message("Guardado con exito")
          .status(HttpStatus.OK)
          .build();
        return categoryResponse;
      }
    }catch(Exception ex){
      System.out.println("Error guardando");
    }
    return categoryResponse;
  }

  @Override
  public Category searchCategory(Integer cId) {
    return categoryRepository.getReferenceById(cId);
  }

  @Override
  public List<CategorysResponse> allCategory() {
    List<CategorysResponse> listCategorys = categoryRepository.findAll()
        .stream()
        .map(x -> mapper.map(x, CategorysResponse.class))
        .collect(Collectors.toList());
    return  listCategorys;
  }
}
