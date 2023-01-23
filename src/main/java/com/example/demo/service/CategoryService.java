package com.example.demo.service;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoriesResponse;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.entity.Category;
import com.example.demo.repository.ICategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

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
  public CategoryResponse createCategory(CategoryRequest categoryRequest) {

    Category category = mapper.map(categoryRequest, Category.class);
    try {
      if (existCategory(category.getCId())) {
        return CategoryResponse.builder()
            .message("la categoria ya existe")
            .status(HttpStatus.BAD_REQUEST)
            .build();
      }
      var response = categoryRepository.save(category);
      return CategoryResponse.builder()
          .cId(response.getCId())
          .message("Guardado con exito")
          .status(HttpStatus.OK)
          .build();

    } catch (Exception ignored) {

    }
    return null;
  }

  @Override
  public Category searchCategory(Integer cId) {
    return categoryRepository.getReferenceById(cId);
  }

  @Override
  public List<CategoriesResponse> allCategory() {
    return categoryRepository.findAll()
        .stream()
        .map(x -> mapper.map(x, CategoriesResponse.class))
        .collect(Collectors.toList());
  }
}
