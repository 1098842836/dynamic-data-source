package com.yl.spring.beandefinitionregistry;

import com.yl.spring.beandefinitionregistry.config.BeanDefinition;
import com.yl.spring.beandefinitionregistry.support.DefaultListableBeanFactory;
import org.junit.Test;

public class Test20231205 {

    @Test
    public void test1(){
        /**
         * 1、一个用于Bean信息的类，主要存class；一个是存放Bean实例的工厂类（分 单例工厂 和 普通工厂）
         * 2、首先我们创建一个属于自己BeanDefinition
         * 3、然后我们根据这个class的定义类BeanDefinition去注册到DefaultListableBeanFactory工厂类中（放到Map里）
         * 4、然后我们根据key去获取我们对象，逻辑是：先从单例工厂去寻找，若有直接拿出，若没有，则需要通过class的反射newInstance()去创建实例，
         *    创建同同时并放到单例工厂中，最终返回我们创建的实例；
         */
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(InnerClassDemo.class);
        beanFactory.registryBeanDefinition("InnerClassDemo",beanDefinition);


        InnerClassDemo innerClassDemo = (InnerClassDemo) beanFactory.getBean("InnerClassDemo");
        innerClassDemo.sayHello();


    }




}

