package com.yl.dynamicdatasource.annotation.register;

import com.yl.dynamicdatasource.annotation.EntityHandlerScan;
import com.yl.dynamicdatasource.annotation.EntityHandlerType;
import com.yl.dynamicdatasource.annotation.factory.EntityHandlerFactory;
import com.yl.dynamicdatasource.test.EntityHandler;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.*;

/**
 * @description: 实体处理器注册类
 * @author: yl
 * @date: 2022-07-28
 **/
public class EntityHandlerRegistrar implements ImportBeanDefinitionRegistrar {


    /**
     * 处理类扫描路径
     */
    private static final String BASE_PACKAGE = "dynamicHandlerPackage";


    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        String canonicalName = EntityHandlerScan.class.getCanonicalName();
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(canonicalName);
        String[] dynamicHandlerPackage = (String[]) annotationAttributes.get(BASE_PACKAGE);
        for (String packageName : dynamicHandlerPackage) {
            //1.扫描impl包下的所有类
            Reflections reflections = new Reflections(packageName);
            //2.impl包下类上有EntityHandlerType注解的类
            Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(EntityHandlerType.class);
            for (Class<?> cl : classSet) {
                EntityHandlerType annotation = cl.getAnnotation(EntityHandlerType.class);
                String[] value = annotation.value();
                Arrays.asList(value).forEach(val->{
                    EntityHandlerFactory.register(val,(Class<? extends EntityHandler>)cl);
                });
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cl);
                registry.registerBeanDefinition(cl.getName(), builder.getBeanDefinition());
                System.out.println(cl.getName());
            }
        }
    }
}
