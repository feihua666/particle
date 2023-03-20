package com.particle.dataquery.infrastructure.datasource.mapper;

import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 数据查询数据源接口 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Mapper
public interface DataQueryDatasourceApiMapper extends IBaseMapper<DataQueryDatasourceApiDO> {

}
