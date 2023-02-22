package com.yl.dynamicdatasource.test.controller;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @description:
 * @author: yl
 * @date: 2023-02-01
 **/
@WebService
public class WebServiceTestService {


    public String getName(String name){
        System.out.println("调用一次了----");
        return "获取WebService接口的信息："+name;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8777/Service/ServiceHello", new WebServiceTestService());
        System.out.println("发布成功!");
    }


}
