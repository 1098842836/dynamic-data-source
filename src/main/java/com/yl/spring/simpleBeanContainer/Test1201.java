package com.yl.spring.simpleBeanContainer;

import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.ObjectAssert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class Test1201 {


    @Test
    public void testBean() {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("DemoService", new DemoService());
        DemoService demoService = (DemoService) beanFactory.getBean("DemoService");
        ObjectAssert<DemoService> notNull = assertThat(demoService).isNotNull();
        AbstractStringAssert<?> hello = assertThat(demoService.sayHello()).isEqualTo("hello");


    }


    public class DemoService {

        public String sayHello() {
            System.out.println("hello");
            return "hello";
        }
    }
}
