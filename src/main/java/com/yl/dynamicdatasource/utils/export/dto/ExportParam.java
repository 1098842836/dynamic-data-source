package com.yl.dynamicdatasource.utils.export.dto;

import lombok.experimental.Accessors;

/**
 * @author: yl
 **/
@Accessors
public class ExportParam {

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 分页大小
     */
    private Integer pageSize;

    /**
     * 排序
     */
    private String orderBy;

    public Integer getPageNum() {
        return pageNum;
    }

    public ExportParam setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public ExportParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public ExportParam setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public static ExportParam build(){
        return new ExportParam();
    }







}
