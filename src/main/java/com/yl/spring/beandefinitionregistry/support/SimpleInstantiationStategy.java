package com.yl.spring.beandefinitionregistry.support;

import com.yl.spring.beandefinitionregistry.config.BeanDefinition;
import com.yl.spring.beandefinitionregistry.exception.BeanException;

import java.lang.reflect.Constructor;

public class SimpleInstantiationStategy implements InstantiationStrategy{

    @Override
    public Object createBean(BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor declaredConstructor = beanClass.getDeclaredConstructor();
            Object instance = declaredConstructor.newInstance();
            return instance;

        }catch (Exception e){
            throw  new BeanException("创建无参实例失败",e);
        }
    }
}
