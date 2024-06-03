package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    void insertCategory(CategoryDTO categoryDTO);

    PageResult selectCatPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void deleteByID(Long id);

    void updateByID(CategoryDTO categoryDTO);
}
