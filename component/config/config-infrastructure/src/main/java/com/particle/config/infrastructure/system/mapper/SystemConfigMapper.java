package com.particle.config.infrastructure.system.mapper;

import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 系统参数配置 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Mapper
public interface SystemConfigMapper extends IBaseMapper<SystemConfigDO> {

}
