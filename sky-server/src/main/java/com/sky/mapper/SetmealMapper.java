package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SetmealMapper {

    @Select("Select count(*) from setmeal where category_id = #{id}")
    int countByCategoryId(Long id);
}
