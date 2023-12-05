package com.yl.spring.beandefinitionregistry.support;

import com.yl.spring.beandefinitionregistry.config.BeanDefinition;
import com.yl.spring.beandefinitionregistry.exception.BeanException;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstarctAutowiredCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeanException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
