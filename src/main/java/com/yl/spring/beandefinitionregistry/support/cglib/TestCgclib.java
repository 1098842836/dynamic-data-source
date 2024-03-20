package com.yl.spring.beandefinitionregistry.support.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;

public class TestCgclib {
    public static void main(String[] args) {
        /** 动态代理创建的字节码文件存储到本地 */
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "com.yl.spring.beandefinitionregistry.support.cglib");
        /** 通过cg11b动态代理获取代理对象的过程，创建调用的对象 */
        Enhancer enhancer = new Enhancer();
        /** 设置enhancer对象的父类 */
        enhancer.setSuperclass(People.class);
        /** 设置enhancer的回调对象 */
        enhancer.setCallback(new MyCglib());
        /** 创建代理对象 */
        People people = (People) enhancer.create();
        /** 通过代理对象调用目标方法 */
//        people.B(999);
        System.out.println(people.getClass());

    }
}
