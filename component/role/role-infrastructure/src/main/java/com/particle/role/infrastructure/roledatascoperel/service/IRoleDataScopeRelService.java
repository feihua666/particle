package com.particle.role.infrastructure.roledatascoperel.service;

import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 角色数据范围关系 服务类
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
public interface IRoleDataScopeRelService extends IBaseService<RoleDataScopeRelDO> {

    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    default List<RoleDataScopeRelDO> getByRoleId(Long roleId) {
        Assert.notNull(roleId,"roleId 不能为空");
        return list(Wrappers.<RoleDataScopeRelDO>lambdaQuery().eq(RoleDataScopeRelDO::getRoleId, roleId));
    }



    /**
     * 根据角色id查询多个
     * @param roleIds
     * @return
     */
    default List<RoleDataScopeRelDO> getByRoleIds(List<Long> roleIds) {
        Assert.notEmpty(roleIds,"roleIds 不能为空");
        return list(Wrappers.<RoleDataScopeRelDO>lambdaQuery().in(RoleDataScopeRelDO::getRoleId, roleIds));
    }
            

    /**
     * 根据数据范围id查询
     * @param dataScopeId
     * @return
     */
    default RoleDataScopeRelDO getByDataScopeId(Long dataScopeId) {
        Assert.notNull(dataScopeId,"dataScopeId 不能为空");
        return getOne(Wrappers.<RoleDataScopeRelDO>lambdaQuery().eq(RoleDataScopeRelDO::getDataScopeId, dataScopeId));
    }



    /**
     * 根据数据范围id查询多个
     * @param dataScopeIds
     * @return
     */
    default List<RoleDataScopeRelDO> getByDataScopeIds(List<Long> dataScopeIds) {
        Assert.notEmpty(dataScopeIds,"dataScopeIds 不能为空");
        return list(Wrappers.<RoleDataScopeRelDO>lambdaQuery().in(RoleDataScopeRelDO::getDataScopeId, dataScopeIds));
    }
            








}
