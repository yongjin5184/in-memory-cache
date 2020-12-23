package com.mall.commerce.service;

import com.mall.commerce.controller.dto.CreateCategoryRequest;
import com.mall.commerce.controller.dto.UpdateCategoryRequest;
import com.mall.commerce.entity.Category;
import com.mall.commerce.exception.NotExistCategoryException;
import com.mall.commerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     *
     * @param categoryId
     * @return 카테고리 ID 에 해당하는 카테고리
     */
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(NotExistCategoryException::new);
    }

    /**
     *
     * @param categoryId
     * @param updateCategoryRequest
     * @return 변경된 카테고리
     */
    @Transactional
    public Category update(Long categoryId, UpdateCategoryRequest updateCategoryRequest) {
        Category findCategory = findById(categoryId);

        findCategory.update(updateCategoryRequest.getCategoryName());

        return findCategory;
    }

    /**
     *
     * @return 모든 카테고리 리스트
     */
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     *
     * @param categoryName
     * @return 카테고리 이름에 해당하는 카테고리
     */
    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    /**
     *
     * @param parentId
     * @return 부모 카테고리를 갖는 카테고리 리스트
     */
    public List<Category> findAllCategoriesByParentId(Long parentId) {
        return categoryRepository.findAllByParentId(parentId);
    }

    @Transactional
    public Category create(CreateCategoryRequest createCategoryRequest) {
        Category category = Category.builder()
                .categoryName(createCategoryRequest.getCategoryName())
                .parentId(createCategoryRequest.getParentId())
                .depth(createCategoryRequest.getDepth()).build();

        return categoryRepository.save(category);
    }
}
