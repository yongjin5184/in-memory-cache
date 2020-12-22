package com.mall.commerce.controller;

import com.mall.commerce.common.BaseControllerTest;
import com.mall.commerce.repository.CategoryRepository;
import com.mall.commerce.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CategoryControllerTest extends BaseControllerTest {

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리를 변경한다.")
    void testExample() throws Exception {
        this.mockMvc.perform(put("/v1/category/1")
                .content("{\"categoryName\" : \"변경된카테고리\"}")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}