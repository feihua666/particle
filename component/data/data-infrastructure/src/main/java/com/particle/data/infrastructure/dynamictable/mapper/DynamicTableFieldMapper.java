package com.particle.data.infrastructure.dynamictable.mapper;

import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 动态数据表格字段 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Mapper
public interface DynamicTableFieldMapper extends IBaseMapper<DynamicTableFieldDO> {

}
