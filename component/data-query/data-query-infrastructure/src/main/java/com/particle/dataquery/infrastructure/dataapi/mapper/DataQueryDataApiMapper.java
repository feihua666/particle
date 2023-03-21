package com.particle.dataquery.infrastructure.dataapi.mapper;

import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 数据查询数据接口 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Mapper
public interface DataQueryDataApiMapper extends IBaseMapper<DataQueryDataApiDO> {

}
