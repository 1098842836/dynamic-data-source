package com.yl.dynamicdatasource.utils.export.controller;


import com.yl.dynamicdatasource.config.DynamicDataSourceContextHolder;
import com.yl.dynamicdatasource.utils.export.EasyExcelExportUtils;
import com.yl.dynamicdatasource.utils.export.dto.ExportParam;
import com.yl.dynamicdatasource.utils.export.dto.RespoolLocalNet;
import com.yl.dynamicdatasource.utils.export.mapper.TransFtpPortTestMapper;
import com.yl.dynamicdatasource.utils.export.dto.TransFtpPort;
import com.yl.dynamicdatasource.utils.export.mapper.RespoolLocalNetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RequestMapping("/easyExcel")
@RestController
public class TestEasyExcelController {

    @Autowired
    TransFtpPortTestMapper transFtpPortTestMapper;

    @Autowired
    RespoolLocalNetMapper respoolLocalNetMapper;


    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        DynamicDataSourceContextHolder.setDb("249");
        String sql="select *  from T_TRANS_FTPPORT_TEMP where 1=1";

        /**
         * 必备要求：
         * 1、sql语句
         * 2、一个实体类（例如TransFtpPort），可以自定义header、设置header或者内容的style
         * 3、一个继承DynamicSqlMapper的mapper类（主要作用是传入泛型实体类TransFtpPort）
         */
        EasyExcelExportUtils.export(
                sql,
                "测试百万数据导出",
                ExportParam.build().setPageSize(3000).setOrderBy("UPDATE_STATUS"),
                TransFtpPort.class,
                transFtpPortTestMapper,
                response);

    }
}
