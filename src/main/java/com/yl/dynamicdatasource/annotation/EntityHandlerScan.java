package com.yl.dynamicdatasource.annotation;

import com.yl.dynamicdatasource.annotation.register.EntityHandlerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Description //规格对应的实体的处理器扫描注解
 * @Date 2022/7/28
 * @Author yl
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({EntityHandlerRegistrar.class})
public @interface EntityHandlerScan {

    /**
     *  获取实体处理器的路径
     */
    String[] dynamicHandlerPackage() default {};

}
