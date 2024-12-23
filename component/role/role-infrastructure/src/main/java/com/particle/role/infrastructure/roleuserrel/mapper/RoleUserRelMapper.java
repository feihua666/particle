package com.particle.role.infrastructure.roleuserrel.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 角色用户关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Mapper
public interface RoleUserRelMapper extends IBaseMapper<RoleUserRelDO> {

}
