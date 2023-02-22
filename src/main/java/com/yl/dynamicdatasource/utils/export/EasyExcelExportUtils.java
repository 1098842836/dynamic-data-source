package com.yl.dynamicdatasource.utils.export;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yl.dynamicdatasource.common.mapper.DynamicSqlMapper;
import com.yl.dynamicdatasource.utils.DateUtils;
import com.yl.dynamicdatasource.utils.export.dto.ExportParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @description: 基于阿里巴巴的EasyExcel框架大数据导出
 * @author: yl
 **/
public class EasyExcelExportUtils {


    private static final Logger log = LoggerFactory.getLogger(EasyExcelExportUtils.class);

    /**
     * 默认pageSize大小
     */
    private static Integer DEFAULT_PAGESIZE = 500;

    public static void export(String sql,
                              String fileName,
                              ExportParam exportParam,
                              Class<?> clazz,
                              DynamicSqlMapper dynamicSqlMapper,
                              HttpServletResponse response) throws IOException {

        //先设置response的格式
        response.setContentType("application/force-download");
        response.setCharacterEncoding("utf-8");
        String outFileName = URLEncoder.encode(fileName + DateUtils.formatDate(new Date(),DateUtils.YYYYMMDDHHMMSS) + ".xlsx", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + outFileName);

        ExcelWriter build = new ExcelWriterBuilder()
                .autoCloseStream(true)
                .excelType(ExcelTypeEnum.XLSX)
                .file(response.getOutputStream())
                .head(clazz)
                .build();

        // xlsx文件上上限是104W行左右,这里如果超过104W需要分Sheet
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName("sheet01");

        int pageNum = (exportParam != null && exportParam.getPageNum() != null) ? exportParam.getPageNum() : 1;
        int pageSize = (exportParam != null && exportParam.getPageSize() != null && exportParam.getPageSize() >= DEFAULT_PAGESIZE) ? exportParam.getPageSize() : DEFAULT_PAGESIZE;
        String orderBy = exportParam != null ? exportParam.getOrderBy() : "";

        long begin = System.currentTimeMillis();
        List list = null;
        PageHelper.startPage(pageNum, pageSize, orderBy);
        list = dynamicSqlMapper.queryBatch(sql);
        long total = new PageInfo<>(list).getTotal();
        build.write(list, writeSheet);
        while ((pageNum - 1) * pageSize < total) {
            pageNum++;
            Page<Object> page = PageHelper.startPage(pageNum, pageSize, false);
            // 要禁止查询cout(0)，就得手动添加orderBy
            page.setOrderBy(orderBy);
            list = dynamicSqlMapper.queryBatch(sql);
            build.write(list, writeSheet);
        }
        build.finish();
        long end = System.currentTimeMillis();
        log.info("接口总时间花费："+(end-begin));

    }


}
