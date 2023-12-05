package com.yl.spring.simpleBeanContainer;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {

    private Map<String, Object> map = new HashMap<>();

    public Object getBean(String name) {
        return map.get(name);
    }

    public void registerBean(String name,Object obj) {
        map.put(name,obj);
    }
}
