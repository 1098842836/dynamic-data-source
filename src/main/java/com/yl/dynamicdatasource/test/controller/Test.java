package com.yl.dynamicdatasource.test.controller;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.json.JSONUtil;
import com.yl.dynamicdatasource.annotation.EntityHandlerScan;
import com.yl.dynamicdatasource.test.model.Person;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.util.*;

/**
 * @description:
 * @author: yl
 * @date: 2022-07-29
 **/
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
//        Class c1 = Class.forName("com.yl.dynamicdatasource.annotation.EntityHandlerScan");
//
//        //通过反射获得注解
//        Annotation[] annotations = c1.getAnnotations();
//        Arrays.asList(annotations).forEach(mm->{
//            //打印头上有哪些注解
//            System.out.println(mm);
//        });
//
//        //获取头上注解的值
//        Target target = (Target)c1.getAnnotation(Target.class);
//        ElementType[] value = target.value();
//        for (ElementType e:value){
//            System.out.println(e);
//        }


//        AnnotationMetadata standardAnnotationMetadata = AnnotationMetadata.introspect(StandardAnnotationMetadata.class);
//        Map<String, Object> attributes = standardAnnotationMetadata.getAnnotationAttributes(EntityHandlerScan.class.getCanonicalName());
//        String[] dynamicHandlerPackage =(String[]) attributes.get("dynamicHandlerPackage");
//        System.out.println();


        Person person = new Person(18,20);

        Person person1 = new Person(18,20);

        System.out.println(person.hashCode());
        System.out.println(person1.hashCode());
        System.out.println(person.equals(person1));
        System.out.println(person == person1);

        Set<Person> set = new HashSet<>();
        set.add(person);
        set.add(person1);
        System.out.println(JSONUtil.toJsonStr(set));

        System.out.println("------");

        String str="1111";
        String str2="1111";
        System.out.println(str==str2);

    }
}
