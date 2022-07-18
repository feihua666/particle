package com.particle.area.infrastructure.mapper;

import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 区域 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Mapper
public interface AreaMapper extends IBaseMapper<AreaDO> {

}
