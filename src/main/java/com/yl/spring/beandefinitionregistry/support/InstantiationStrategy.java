package com.yl.spring.beandefinitionregistry.support;

import com.yl.spring.beandefinitionregistry.config.BeanDefinition;

public interface InstantiationStrategy {

    Object createBean(BeanDefinition beanDefinition);

}
