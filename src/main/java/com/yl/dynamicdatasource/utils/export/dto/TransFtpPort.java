package com.yl.dynamicdatasource.utils.export.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author: yl
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class TransFtpPort{

    @Id
    @ColumnWidth(40)
    @ExcelProperty(value = "端口ID")
    private String PORT_ID;

    @ColumnWidth(100)
    @ExcelProperty(value = "名字")
    private String VENDOR_OBJ_NAME;

    @ColumnWidth(20)
    @ExcelProperty(value = {"有关修改的信息","修改状态"})
    @ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 17)
    private Integer UPDATE_STATUS;


    /**
     * fillBackgroundColor的颜色选择参考 org.apache.poi 的 IndexedColors 类
     * ContentStyle是设置header下面内容的风格
     */
    @ColumnWidth(20)
    @ExcelProperty(value = {"有关修改的信息","修改时间"})
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    @HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 10)
    @ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 40)
    private Date UPDATE_TIME;


    private String ID;
}
