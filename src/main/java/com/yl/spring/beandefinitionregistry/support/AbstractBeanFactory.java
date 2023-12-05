package com.yl.spring.beandefinitionregistry.support;

import com.yl.spring.beandefinitionregistry.BeanFactory;
import com.yl.spring.beandefinitionregistry.config.BeanDefinition;
import com.yl.spring.beandefinitionregistry.exception.BeanException;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeanException {
        Object singletonBean = getSingleton(beanName);
        if (singletonBean != null) {
            return singletonBean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException;
}
