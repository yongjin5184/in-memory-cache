package com.mall.commerce.controller;

import com.mall.commerce.controller.dto.CategoryResponse;
import com.mall.commerce.controller.dto.UpdateCategoryRequest;
import com.mall.commerce.entity.Category;
import com.mall.commerce.entity.Product;
import com.mall.commerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    /**
     *
     *  특정 카테고리를 변경한다.
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") Long categoryId, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryService.update(categoryId, updateCategoryRequest);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(CategoryResponse.of(category));
    }

    /**
     *
     * 전체 카테고리 리스트를 조회한다.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }
}
