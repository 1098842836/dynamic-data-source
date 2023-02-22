package com.yl.dynamicdatasource.annotationtest.one;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description:
 * @author: yl
 * @date: 2022-07-28
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ImportAnnotionTest {
    @Autowired
    TestA testA;

    @Autowired
    TestB testb;

    @Autowired
    TestC testC;

    @Test
    public void TestA() {
        testA.printName();
        testb.printName();
        testC.printName();
    }

}
