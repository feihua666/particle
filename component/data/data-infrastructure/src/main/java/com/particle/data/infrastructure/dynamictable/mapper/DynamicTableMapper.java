package com.particle.data.infrastructure.dynamictable.mapper;

import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 动态数据表格 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Mapper
public interface DynamicTableMapper extends IBaseMapper<DynamicTableDO> {

}
