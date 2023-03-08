package com.particle.dataquery.infrastructure.provider.mapper;

import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 数据查询供应商 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Mapper
public interface DataQueryProviderMapper extends IBaseMapper<DataQueryProviderDO> {

}
