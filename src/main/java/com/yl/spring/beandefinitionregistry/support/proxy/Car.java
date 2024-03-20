package com.yl.spring.beandefinitionregistry.support.proxy;

public class Car implements CarInterface {
    @Override
    public void run() {
        System.out.println("run方法启动，汽车行驶---");
    }

    @Override
    public String carRefueling(int i) {
        System.out.println("执行了carRefueling()方法，汽车加油中---");
        return "加油量："+i+"L";
    }
}
