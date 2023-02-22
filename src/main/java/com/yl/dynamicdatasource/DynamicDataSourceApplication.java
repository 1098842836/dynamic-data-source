package com.yl.dynamicdatasource;

import cn.hutool.json.JSONUtil;
import com.yl.dynamicdatasource.annotation.EntityHandlerScan;
import com.yl.dynamicdatasource.config.DynamicDruidConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.MultiValueMap;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.util.Set;


@MapperScan("com.yl.dynamicdatasource")
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        },
        scanBasePackages = {
                "com.yl"
        })
@EntityHandlerScan(dynamicHandlerPackage = {
        "com.yl.dynamicdatasource.test.handle",
        "com.yl.dynamicdatasource.formal.handle"
})
public class DynamicDataSourceApplication {

    public static void main(String[] args) throws IOException {
        String canonicalName = EntityHandlerScan.class.getCanonicalName();
        System.out.println(canonicalName);
        AnnotationMetadata standardAnnotationMetadata = AnnotationMetadata.introspect(DynamicDataSourceApplication.class);


        //根据“全类名”判断是否是   直接注解或者元注解(通俗讲就是判断是否是注解)
        boolean isannotated = standardAnnotationMetadata.isAnnotated(canonicalName);
        System.out.println("是否是”直接注解或者元注解：“"+isannotated);

        //根据“全类名”获取所有注解的属性
        MultiValueMap<String, Object> allAnnotationAttributes = standardAnnotationMetadata.getAllAnnotationAttributes(canonicalName);
        System.out.println(JSONUtil.toJsonStr(allAnnotationAttributes.toSingleValueMap()));




        //返回类头上所有的注解的全类名
        Set<String> annotationTypes = standardAnnotationMetadata.getAnnotationTypes();
        System.out.println(annotationTypes.toString());

        //获取指定元注解上的  元注解的全类名（不包括常用的@Documented、@Retention、@Target）
        Set<String> metaAnnotationTypes = standardAnnotationMetadata.getMetaAnnotationTypes(canonicalName);
        System.out.println("获取指定注解上的元注解："+metaAnnotationTypes.toString());

        //是否存在被指定 直接注解或元注解 标注的方法
        boolean b = standardAnnotationMetadata.hasAnnotatedMethods(canonicalName);
        System.out.println("是否存在被指定 直接注解或元注解 标注的方法:"+b);

        //返回上述方法的 MethodMetadata 集合
        Set<MethodMetadata> annotatedMethods = standardAnnotationMetadata.getAnnotatedMethods(canonicalName);
        System.out.println("返回上述方法的 MethodMetadata 集合:"+annotatedMethods.toString());


        AnnotationMetadata configAnnotationMetaData = AnnotationMetadata.introspect(DynamicDruidConfig.class);
        Set<MethodMetadata> annotatedMethods1 = configAnnotationMetaData.getAnnotatedMethods(Bean.class.getCanonicalName());
        System.out.println("返回DynamicDruidConfig上述方法的 MethodMetadata 集合:"+annotatedMethods1.toString());

        MetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader metadataReader = factory.getMetadataReader(DynamicDruidConfig.class.getName());
        AnnotationMetadata metadata = metadataReader.getAnnotationMetadata();
        metadata.isAnnotated(canonicalName);
        metadata.getAllAnnotationAttributes(canonicalName);
        Set<MethodMetadata> annotatedMethods2 = metadata.getAnnotatedMethods(Bean.class.getCanonicalName());
        System.out.println("基于ASM返回DynamicDruidConfig上述方法的 MethodMetadata 集合:"+annotatedMethods2.toString());



        SpringApplication.run(DynamicDataSourceApplication.class, args);
    }

}
