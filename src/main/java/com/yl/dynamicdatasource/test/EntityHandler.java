package com.yl.dynamicdatasource.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实体处理器接口
 *
 * @author Kang
 * @Date 2021-09-17
 */
public interface EntityHandler {
    /**
     * get后置回调
     *
     * @param props 实体Map
     */
    default void afterGet(Map<String, Object> props) {
    }

    /**
     * getListByExample前置回调
     */
    default void beforeGetListByExample() {
    }



    /**
     * getList前置回调
     *
     * @param specId 规格
     * @param params 查询参数
     */
    default void beforeGetList(Long specId, Map<String, Object> params) {
    }

    /**
     * getList后置回调
     *
     * @param specId 规格
     * @param list   列表数据
     * @param params 查询参数
     */
    default void afterGetList(Long specId, List<Map<String, Object>> list, Map<String, Object> params) {
    }

    /**
     * insert前置回调
     *
     * @param props 实体属性
     */
    default void beforeInsert(Map<String, Object> props) {
    }

    /**
     * insert后置回调
     *
     * @param props 实体属性
     */
    default void afterInsert(Map<String, Object> props) {

    }

    /**
     * insertList前置回调
     *
     * @param specId 规格ID
     * @param list   实体列表
     */
    default void beforeInsertList(Long specId, List<Map<String, Object>> list) {
    }

    /**
     * insertList后置回调
     *
     * @param specId 规格ID
     * @param list   实体列表
     */
    default void afterInsertList(Long specId, List<Map<String, Object>> list) {
        list.forEach(o->afterInsert(o));
    }

    /**
     * update前置回调
     *
     * @param props    实体属性
     * @param oldProps 修改前实体属性
     */
    default void beforeUpdate(Map<String, Object> props, Map<String, Object> oldProps) {

    }

    /**
     * update后置回调
     *
     * @param props    实体属性
     * @param oldProps 修改前实体属性
     */
    default void afterUpdate(Map<String, Object> props, Map<String, Object> oldProps) {

    }

    /**
     * logicDelete前置回调
     *
     * @param specId 规格ID
     * @param key    主键
     * @return List<String>
     */
    default List<String> beforeDelete(Long specId, Object key) {
        return new ArrayList<>();
    }

    /**
     * logicDelete后置回调
     *
     * @param specId 规格ID
     * @param key    主键
     * @param props  属性
     */
    default void afterDelete(Long specId, Object key, Map<String, Object> props) {

    }

    /**
     * delete前置回调
     *
     * @param specId 规格ID
     * @param key    主键
     * @return List<String>
     */
    default List<String> beforePhysicDelete(Long specId, Object key) {
        return new ArrayList<>();
    }

    /**
     * delete后置回调
     *
     * @param specId 规格ID
     * @param key    主键
     * @param props  属性
     */
    default void afterPhysicDelete(Long specId, Object key, Map<String, Object> props) {

    }

    /**
     * 记录日志
     *
     * @param optType 操作类型
     * @param props   属性
     */
    default void writeLog(String optType, Map<String, Object> props) {

    }

}
