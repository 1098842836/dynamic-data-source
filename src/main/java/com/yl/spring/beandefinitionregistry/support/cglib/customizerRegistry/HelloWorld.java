package com.yl.spring.beandefinitionregistry.support.cglib.customizerRegistry;


import jdk.internal.org.objectweb.asm.Opcodes;

public class HelloWorld<T> {

    public HelloWorld() {

    }
    public static void main(String[] var0) {
        System.out.println("Hello World");
    }

    public String test(T t){
        int i=65;
        return "kkkk";
    }
}
