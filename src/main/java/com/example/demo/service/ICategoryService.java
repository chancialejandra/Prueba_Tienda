package com.example.demo.service;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.CategoriesResponse;
import com.example.demo.entity.Category;
import java.util.List;

public interface ICategoryService {

  Boolean existCategory(Integer cId);
  CategoryResponse createCategory(CategoryRequest category);
  Category searchCategory(Integer cId);
  List<CategoriesResponse> allCategory();

}
