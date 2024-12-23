package com.particle.openplatform.infrastructure.openapi.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 开放接口批量查询记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Mapper
public interface OpenplatformOpenapiBatchQueryRecordMapper extends IBaseMapper<OpenplatformOpenapiBatchQueryRecordDO> {

}
