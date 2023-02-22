package com.yl.dynamicdatasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 数据源切换处理
 * @author: yl
 * @date: 2022-07-21
 **/
public class DynamicDataSourceContextHolder {

    public static final Logger log = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    /**
     * 切换数据源
     */
    public static void setDb(String dbName){
        log.info("切换到{}数据源", dbName);
        threadLocal.set(dbName);
    }

    /**
     * 获得数据源
     */
    public static String getDb(){
        log.info("获得{}数据源", threadLocal.get());
        return threadLocal.get();
    }

}
