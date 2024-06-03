package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新增分类")
    @PostMapping("/admin/category")
    public Result<String> insertCategory(CategoryDTO categoryDTO){

        categoryService.insertCategory(categoryDTO);

        return Result.success();
    }

    @ApiOperation("分类分页查询")
    @GetMapping("/admin/category/page")
    public Result<PageResult> selectCatPage(CategoryPageQueryDTO categoryPageQueryDTO){

        PageResult pageResult = categoryService.selectCatPage(categoryPageQueryDTO);

        return Result.success(pageResult);
    }

}
