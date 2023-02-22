package com.yl.dynamicdatasource.test.controller;

/**
 * @description:
 * @author: yl
 * @date: 2022-08-25
 **/
public class Test01 {
    public static void main(String[] args) {

        //校验QQ号码 1、要求必须是5-15数字 2、0不能开头
        String qq = "604154942";
        String regex="[1-9][0-9]{4,14}";
        if(qq.matches(regex)){
            System.out.println("校验QQ号码 1、要求必须是5-15数字 2、0不能开头：匹配成功");
        }else{
            System.out.println("校验QQ号码 1、要求必须是5-15数字 2、0不能开头：匹配不成功");

        }

        //举例：校验手机号码
        // 1：要求为 11 位数字
        //2：第 1 位为 1，第 2 位为 3、4、5、7、8 中的一个，后面 9 位为 0 到 9 之间的任意数字。
        String regex2="1[345678][0-9]{9}";
        String phone = "18810022666";
        if (phone.matches(regex2)){
            System.out.println("匹配电话号码成功");
        }else{
            System.out.println("匹配电话号码不成功");

        }

        //举例：将字符串中的数字全部替换成！号
        String str = "1a2b3c4d";
        String s = str.replaceAll("\\\\d", "!");
        System.out.println("替换成！号后的字符串是："+s);


        //根据空格切割字符串
        String str01 = "我 很 帅";
        String[] s1 = str01.split(" ");
        for (String ss:s1){
            System.out.println(ss);
        }


        //根据.切割 IP 地址
        String str02 = "192.168.11.88";
        String[] s2 = str02.split("\\.");
        for (String ss:s2){
            System.out.println(ss);
        }





    }
}
