package com.yl.spring.beandefinitionregistry.support.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestProxy {
    public static void main(String[] args) {
        Car car = new Car();
//        InvocationHandler invocationHandler005 = new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Object invoke = method.invoke(car, args);
//                System.out.println("InvocationHandler是和代理对象关联起来了");
//                return invoke;
//            }
//        };
//        $Proxy1000 proxyObj = new $Proxy1000(invocationHandler005);
//        proxyObj.carRefueling(2);

        CarInterface proxy = CarProxy.getProxy(car);
        proxy.carRefueling(8);

    }
}
