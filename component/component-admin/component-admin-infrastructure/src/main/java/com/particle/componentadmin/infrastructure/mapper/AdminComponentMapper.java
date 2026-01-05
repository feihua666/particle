package com.particle.componentadmin.infrastructure.mapper;

import com.particle.componentadmin.infrastructure.dos.AdminComponentDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 组件 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Mapper
public interface AdminComponentMapper extends IBaseMapper<AdminComponentDO> {

}
