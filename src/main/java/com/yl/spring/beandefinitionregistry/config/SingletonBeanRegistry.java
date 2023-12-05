package com.yl.spring.beandefinitionregistry.config;



/**
 * 单例注册
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);



}
