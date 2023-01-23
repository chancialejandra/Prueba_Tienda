package com.example.demo.unit.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.demo.dto.response.CategoriesResponse;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.service.ICategoryService;
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
public class CategoryControllerTest {

  @InjectMocks
  CategoryController categoryController;

  @Mock
  ICategoryService categoryService;

  @Nested
  class whenSearchCategories {

    @Test
    public void shouldReturnAllCategoriesWithStatusOk_WhenExists() {

      //Given
      List<CategoriesResponse> categories = buildCategories();

      //When
      when(categoryService.allCategory()).thenReturn(categories);

      var response = categoryController.allCategory();

      //Then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void shouldReturnStatusNoContent_WhenNotExistsCategories() {

      //When
      when(categoryService.allCategory()).thenReturn(null);

      var response = categoryController.allCategory();

      //Then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

  }

  @Nested
  class whenCreateCategory {
    @Test
    public void shouldReturnStatusOk_WhenCreateCategorySuccessfully() {
      //Given
      CategoryResponse category = CategoryResponse.builder()
          .cId(1)
          .status(HttpStatus.OK)
          .message("")
          .build();

      //When
      when(categoryService.createCategory(any())).thenReturn(category);

      var response = categoryController.createCategory(any());

      //Then
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
  }
  // TODO: Validar los diferentes casos y servicios de categorías

  private List<CategoriesResponse> buildCategories() {

    CategoriesResponse category1 = CategoriesResponse.builder().cId(1).title("Granos").build();
    CategoriesResponse category2 = CategoriesResponse.builder().cId(2).title("Lacteos").build();

    List<CategoriesResponse> categories = new ArrayList<>();

    categories.add(category1);
    categories.add(category2);

    return categories;
  }

}





