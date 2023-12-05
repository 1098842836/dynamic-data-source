package com.yl.spring.beandefinitionregistry.support;

import com.yl.spring.beandefinitionregistry.config.BeanDefinition;

/**
 * BeanDefinition的注册接口
 */
public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
