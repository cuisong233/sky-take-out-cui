package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 指定注解对应对象
@Retention(RetentionPolicy.RUNTIME)//指定注解生效时间
public @interface AutoFill {
    // 数据库操作类型， UPDATE INSERT
    OperationType value();
}
