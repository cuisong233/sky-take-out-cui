package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect //表明这是个切面类
@Component //交给 IOC管理
@Slf4j
public class AutoFillAspect {

    //切入点
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    //前置通知
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        log.info("开始公共字段填充");

        // 确定被拦截的数据库操作的方法类型（update insert）
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); //方法的签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class); //获得方法的注解对象
        OperationType operationType = autoFill.value(); // 获得注解的 内容

        // 获取当前被拦截的方法参数（实体对象）
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }
        // 约定第一个是参数
        Object entity = args[0];

        // 准备赋值数据（时间， 当前登录用户id）
        LocalDateTime now = LocalDateTime.now();
        Long currutId = BaseContext.getCurrentId();

        // 根据不同操作类型 对应赋值——使用反射
        if(operationType == OperationType.INSERT){
            //获取方法对象
            Method setCreateTime = entity.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
            Method setCreateUser = entity.getClass().getDeclaredMethod("setCreateUser", Long.class);
            Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
            Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);

            // 调用方法赋值
            setCreateTime.invoke(entity, now);
            setCreateUser.invoke(entity, currutId);
            setUpdateTime.invoke(entity, now);
            setUpdateUser.invoke(entity, currutId);

        }else if(operationType == OperationType.UPDATE){
            Method setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
            Method setUpdateUser = entity.getClass().getDeclaredMethod("setUpdateUser", Long.class);

            setUpdateTime.invoke(entity, now);
            setUpdateUser.invoke(entity, currutId);
        }

    }

}
