package com.particle.lowcode.infrastructure.generator.mapper;

import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 低代码模型 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Mapper
public interface LowcodeModelMapper extends IBaseMapper<LowcodeModelDO> {

}
