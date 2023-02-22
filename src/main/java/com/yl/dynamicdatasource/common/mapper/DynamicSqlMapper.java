package com.yl.dynamicdatasource.common.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Description //动态sql
 * @Author yl
 */
@Mapper
public interface DynamicSqlMapper<T>{
    /**
     * 新增
     *
     * @param sql 动态SQL
     * @return int
     */
    @Insert("${sql}")
    int execute(@Param("sql") String sql);
    
    @Insert("${sql}")
    int executeByParam(@Param("sql") String sql, @Param("params") Object params);

    /**
     * 查询列表
     *
     * @param sql 动态SQL
     * @return List<Map < String, Object>>
     */
    @Select("${sql}")
    List<Map<String, Object>> query(@Param("sql") String sql);


    @Options(fetchSize = 2000)
    @Select("${sql}")
    List<T> queryBatch(@Param("sql") String sql);


    /**
     * 查询列表
     *
     * @param sql 动态SQL
     * @param params 动态sql内的参数
     * @return List<Map < String, Object>>
     */
    @Select("${sql}")
    List<Map<String, Object>> queryByParam(@Param("sql") String sql, @Param("params") Map<String, Object> params);

    @Select("${sql}")
    List<Map<String, Object>> queryByObject(@Param("sql") String sql, @Param("params") Object params);

}
