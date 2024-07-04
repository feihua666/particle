package com.particle.dataconstraint.infrastructure.mapper;

import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 数据对象 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Mapper
public interface DataObjectMapper extends IBaseMapper<DataObjectDO> {

}
