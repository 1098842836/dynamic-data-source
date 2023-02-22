package com.yl.dynamicdatasource.annotationtest.one;

/**
 * @description:
 * @author: yl
 * @date: 2022-07-28
 **/
public class TestC {

    public void fun(String str) {
        System.out.println(str);
    }

    public void printName() {
        System.out.println("类名 ：" + Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
