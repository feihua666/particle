package com.particle.role.infrastructure.rolefuncrel.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 角色菜单功能关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public interface RoleFuncRelMapper extends IBaseMapper<RoleFuncRelDO> {

}
