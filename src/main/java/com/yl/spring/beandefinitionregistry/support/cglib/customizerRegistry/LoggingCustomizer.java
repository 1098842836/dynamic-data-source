package com.yl.spring.beandefinitionregistry.support.cglib.customizerRegistry;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LoggingCustomizer {

    public static void main(String[] args) {
        // 创建Enhancer对象
        Enhancer enhancer = new Enhancer();

        // 设置被代理类的父类
        enhancer.setSuperclass(MyClass.class);

        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("Customizing method: " + method.getName());
                // 在这里可以插入自己的代码逻辑
                return proxy.invokeSuper(obj, args);
            }
        });



        // 创建代理类并获取实例
        MyClass proxy = (MyClass) enhancer.create();

        // 调用代理类的方法
        System.out.println(proxy.getValue());
    }
}

class MyClass {
    public String getValue() {
        return "Original Value";
    }
}
