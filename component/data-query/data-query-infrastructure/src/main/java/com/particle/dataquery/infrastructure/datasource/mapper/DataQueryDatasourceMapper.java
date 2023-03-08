package com.particle.dataquery.infrastructure.datasource.mapper;

import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 数据查询数据源 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Mapper
public interface DataQueryDatasourceMapper extends IBaseMapper<DataQueryDatasourceDO> {

}
