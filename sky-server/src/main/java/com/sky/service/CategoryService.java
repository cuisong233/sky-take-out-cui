package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    void insertCategory(CategoryDTO categoryDTO);

    PageResult selectCatPage(CategoryPageQueryDTO categoryPageQueryDTO);

    void deleteByID(Long id);

    void updateByID(CategoryDTO categoryDTO);

    void startOrStop(Integer status, Long id);

    List<Category> selectByType(Integer type);
}
