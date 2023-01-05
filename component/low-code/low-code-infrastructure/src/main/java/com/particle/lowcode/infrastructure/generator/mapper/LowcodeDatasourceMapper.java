package com.particle.lowcode.infrastructure.generator.mapper;

import com.particle.lowcode.infrastructure.generator.dos.LowcodeDatasourceDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 低代码数据源 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Mapper
public interface LowcodeDatasourceMapper extends IBaseMapper<LowcodeDatasourceDO> {

}
