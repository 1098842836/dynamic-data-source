package com.yl.spring.beandefinitionregistry;

import com.yl.spring.beandefinitionregistry.exception.BeanException;

/**
 * bean容器
 */
public interface BeanFactory {


    Object getBean(String beanName) throws BeanException;


}
