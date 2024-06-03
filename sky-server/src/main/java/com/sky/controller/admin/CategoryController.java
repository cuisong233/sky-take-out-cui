package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新增分类")
    @PostMapping("/admin/category")
    public Result<String> insertCategory(@RequestBody CategoryDTO categoryDTO){

        categoryService.insertCategory(categoryDTO);

        return Result.success();
    }

    @ApiOperation("分类分页查询")
    @GetMapping("/admin/category/page")
    public Result<PageResult> selectCatPage(CategoryPageQueryDTO categoryPageQueryDTO){

        PageResult pageResult = categoryService.selectCatPage(categoryPageQueryDTO);

        return Result.success(pageResult);
    }

    @ApiOperation("根据ID删除分类")
    @DeleteMapping("/admin/category")
    public Result<String> deleteByID(Long id){

        categoryService.deleteByID(id);

        return Result.success();
    }

    @ApiOperation("修改分类")
    @PutMapping("/admin/category")
    public Result<String> updateByID(@RequestBody CategoryDTO categoryDTO){

        categoryService.updateByID(categoryDTO);

        return Result.success();
    }

    @ApiOperation("启用禁用分类")
    @PostMapping("/admin/category/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id){

        categoryService.startOrStop(status, id);

        return Result.success();
    }
}
