package com.particle.componentadmin.infrastructure.mapper;

import com.particle.componentadmin.infrastructure.dos.AdminComponentDependencyDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 组件依赖关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Mapper
public interface AdminComponentDependencyMapper extends IBaseMapper<AdminComponentDependencyDO> {

}
