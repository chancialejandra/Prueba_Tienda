package com.example.demo.controller;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategorysResponse;
import com.example.demo.entity.Category;
import com.example.demo.service.ICategoryService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nexsys/v1/category/")
public class CategoryController {


  private final ICategoryService categoryService;

  public CategoryController(ICategoryService categoryService) {
    this.categoryService = categoryService;
  }


  @PostMapping()
  public ResponseEntity<?> category(@RequestBody CategoryRequest category) {
    var response = categoryService.category(category);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping()
  public List<CategorysResponse> allCategory() {
    return categoryService.allCategory();
  }
}
