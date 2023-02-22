package com.yl.dynamicdatasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @description: 动态数据源
 * @author: yl
 * @date: 2022-07-21
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * @Description //设置形式由键值对促成的不同key的数据源
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        // 必须添加该句，让方法根据重新赋值的targetDataSource依次根据key关键字
        // 查找数据源,返回DataSource,否则新添加数据源无法识别到
        super.afterPropertiesSet();
    }

    /**
     * @Description //每次执行sql之前都会去执行该方法，获取当前数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDb();
    }
}
