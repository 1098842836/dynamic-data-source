package com.yl.spring.beandefinitionregistry.support.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CarProxy {

    public static CarInterface getProxy(final CarInterface iService){
        //获取类加载器
        ClassLoader classLoader = iService.getClass().getClassLoader();
        //获取接口集合
        Class<?>[] interfaces = iService.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = method.invoke(iService, args);
                System.out.println("我正在调被代理类的方法");
                return invoke;
            }
        };
        Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return (CarInterface)proxyInstance;
    }

}
