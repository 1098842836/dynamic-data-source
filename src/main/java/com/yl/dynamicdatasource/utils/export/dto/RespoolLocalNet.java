package com.yl.dynamicdatasource.utils.export.dto;

import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: yl
 **/
@Data
@Table(name="T_RESPOOL_OBJ_LOCALNET")
public class RespoolLocalNet implements Serializable {

    @Id
    @Column(name = "ID")
    @ColumnType(jdbcType= JdbcType.VARCHAR)
    private String ID;

    @Column(name = "EMS_ID")
    private String EMS_ID;

    @Column(name = "TBLNAME")
    private String TBLNAME;

    @Column(name = "LOCALNET")
    private String LOCALNET;

    @Column(name = "CITY")
    private String CITY;

    @Column(name = "LOCALNET_CODE")
    private String LOCALNET_CODE;

    @Column(name = "SHORTCODE")
    private String SHORTCODE;

    @Column(name = "LOCALNET_ID")
    private String LOCALNET_ID;


}
