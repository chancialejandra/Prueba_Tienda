package com.example.demo.unit.controller;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.service.ICategoryService;
import java.util.Objects;
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
  public ResponseEntity<?> createCategory(@RequestBody CategoryRequest category) {
    var response = categoryService.createCategory(category);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }


  @GetMapping()
  public ResponseEntity<?> allCategory() {

    var categories = categoryService.allCategory();

    if (Objects.isNull(categories)) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(categories);

  }
}
