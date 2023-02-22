package com.yl.dynamicdatasource.annotation;


import java.lang.annotation.*;

/**
 * 实体处理器类型注解
 * @author yl
 * @date 2022-09-18
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityHandlerType {
    /**
     * 同类型规格
     */
    String[] value() default {};
}
