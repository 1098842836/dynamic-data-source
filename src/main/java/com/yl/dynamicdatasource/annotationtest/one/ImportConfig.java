package com.yl.dynamicdatasource.annotationtest.one;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: yl
 * @date: 2022-07-28
 **/
@Import({TestB.class,TestA.class,SelfImportSelector.class})
@Configuration
public class ImportConfig {
}
