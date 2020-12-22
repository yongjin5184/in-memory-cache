package com.mall.commerce.service;

import com.mall.commerce.controller.dto.UpdateCategoryRequest;
import com.mall.commerce.entity.Category;
import com.mall.commerce.exception.NotExistCategoryException;
import com.mall.commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(NotExistCategoryException::new);
    }

    @Transactional
    public Category update(Long categoryId, UpdateCategoryRequest updateCategoryRequest) {
        Category findCategory = findById(categoryId);

        findCategory.update(updateCategoryRequest.getCategoryName());

        return findCategory;
    }
}
