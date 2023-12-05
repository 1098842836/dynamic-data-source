package com.yl.spring.beandefinitionregistry.config;

/**
 * 保存bean的信息，包括class类型、构造参数、是否为单例等
 */
public class BeanDefinition {

    private Class beanClass;

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
}
