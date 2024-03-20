package com.yl.spring.beandefinitionregistry.support.cglib;

public class People  {

    public void A() {
        System.out.println("这是A方法--");
    }

    public String B(int i) {
        System.out.println("执行了B()方法");
        return "这是B()方法. "+i;
    }
}
