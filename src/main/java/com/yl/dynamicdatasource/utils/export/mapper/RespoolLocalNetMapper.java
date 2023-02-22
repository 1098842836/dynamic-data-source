package com.yl.dynamicdatasource.utils.export.mapper;

import com.yl.dynamicdatasource.utils.export.dto.RespoolLocalNet;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.IdsMapper;


@Mapper
public interface RespoolLocalNetMapper extends IdsMapper<RespoolLocalNet> , BaseMapper<RespoolLocalNet> {
}