package com.particle.role.infrastructure.rolefuncrel.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;

import java.util.List;

/**
 * <p>
 * 角色菜单功能关系 服务类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface IRoleFuncRelService extends IBaseService<RoleFuncRelDO> {
    /**
     * 根据 roleIds 查询
     * @param roleIds
     * @return
     */
    default List<RoleFuncRelDO> listByRoleIds(List<Long> roleIds){
        List<RoleFuncRelDO> list = list(Wrappers.<RoleFuncRelDO>lambdaQuery().in(RoleFuncRelDO::getRoleId, roleIds));
        return list;
    }

    /**
     * 根据 funcIds 查询
     * @param funcIds
     * @return
     */
    default List<RoleFuncRelDO> listByFuncIds(List<Long> funcIds){
        List<RoleFuncRelDO> list = list(Wrappers.<RoleFuncRelDO>lambdaQuery().in(RoleFuncRelDO::getFuncId, funcIds));
        return list;
    }
}
