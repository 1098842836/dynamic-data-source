package com.yl.spring.beandefinitionregistry.support;

import com.yl.spring.beandefinitionregistry.config.BeanDefinition;
import com.yl.spring.beandefinitionregistry.exception.BeanException;

public abstract class AbstarctAutowiredCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Object instance = null;
        try {
            instance = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeanException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, instance);
        return instance;
    }
}
