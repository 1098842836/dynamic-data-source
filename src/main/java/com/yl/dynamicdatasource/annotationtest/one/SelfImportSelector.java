package com.yl.dynamicdatasource.annotationtest.one;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @description:
 * @author: yl
 * @date: 2022-07-28
 **/
public class SelfImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.yl.dynamicdatasource.annotationtest.one.TestC"};
    }
}
