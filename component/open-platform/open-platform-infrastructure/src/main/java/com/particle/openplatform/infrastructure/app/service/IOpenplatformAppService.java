package com.particle.openplatform.infrastructure.app.service;

import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 开放平台应用 服务类
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
public interface IOpenplatformAppService extends IBaseService<OpenplatformAppDO> {

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    default OpenplatformAppDO getByName(String name) {
        Assert.notNull(name,"name 不能为空");
        return getOne(Wrappers.<OpenplatformAppDO>lambdaQuery().eq(OpenplatformAppDO::getName, name));
    }



    /**
     * 根据名称查询多个
     * @param names
     * @return
     */
    default List<OpenplatformAppDO> getByNames(List<String> names) {
        Assert.notEmpty(names,"names 不能为空");
        return list(Wrappers.<OpenplatformAppDO>lambdaQuery().in(OpenplatformAppDO::getName, names));
    }
            

    /**
     * 根据appId查询
     * @param appId
     * @return
     */
    default OpenplatformAppDO getByAppId(String appId) {
        Assert.notNull(appId,"appId 不能为空");
        return getOne(Wrappers.<OpenplatformAppDO>lambdaQuery().eq(OpenplatformAppDO::getAppId, appId));
    }



    /**
     * 根据appId查询多个
     * @param appIds
     * @return
     */
    default List<OpenplatformAppDO> getByAppIds(List<String> appIds) {
        Assert.notEmpty(appIds,"appIds 不能为空");
        return list(Wrappers.<OpenplatformAppDO>lambdaQuery().in(OpenplatformAppDO::getAppId, appIds));
    }
            

    /**
     * 根据归属用户id查询
     * @param ownerUserId
     * @return
     */
    default List<OpenplatformAppDO> getByOwnerUserId(Long ownerUserId) {
        Assert.notNull(ownerUserId,"ownerUserId 不能为空");
        return list(Wrappers.<OpenplatformAppDO>lambdaQuery().eq(OpenplatformAppDO::getOwnerUserId, ownerUserId));
    }



    /**
     * 根据归属用户id查询多个
     * @param ownerUserIds
     * @return
     */
    default List<OpenplatformAppDO> getByOwnerUserIds(List<Long> ownerUserIds) {
        Assert.notEmpty(ownerUserIds,"ownerUserIds 不能为空");
        return list(Wrappers.<OpenplatformAppDO>lambdaQuery().in(OpenplatformAppDO::getOwnerUserId, ownerUserIds));
    }
            

    /**
     * 根据归属客户id查询
     * @param ownerCustomerId
     * @return
     */
    default List<OpenplatformAppDO> getByOwnerCustomerId(Long ownerCustomerId) {
        Assert.notNull(ownerCustomerId,"ownerCustomerId 不能为空");
        return list(Wrappers.<OpenplatformAppDO>lambdaQuery().eq(OpenplatformAppDO::getOwnerCustomerId, ownerCustomerId));
    }



    /**
     * 根据归属客户id查询多个
     * @param ownerCustomerIds
     * @return
     */
    default List<OpenplatformAppDO> getByOwnerCustomerIds(List<Long> ownerCustomerIds) {
        Assert.notEmpty(ownerCustomerIds,"ownerCustomerIds 不能为空");
        return list(Wrappers.<OpenplatformAppDO>lambdaQuery().in(OpenplatformAppDO::getOwnerCustomerId, ownerCustomerIds));
    }

    /**
     * 根据appId获取分配的接口权限
     * @param appId
     * @return
     */
    List<String> getPermissionsByAppId(String appId);


}
