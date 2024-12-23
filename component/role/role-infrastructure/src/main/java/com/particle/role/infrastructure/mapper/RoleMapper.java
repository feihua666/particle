package com.particle.role.infrastructure.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.role.infrastructure.dos.RoleDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public interface RoleMapper extends IBaseMapper<RoleDO> {

}
