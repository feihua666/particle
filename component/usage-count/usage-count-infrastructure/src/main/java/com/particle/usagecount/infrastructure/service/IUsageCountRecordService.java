package com.particle.usagecount.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;

import java.util.List;

/**
 * <p>
 * 使用次数记录 服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
public interface IUsageCountRecordService extends IBaseService<UsageCountRecordDO> {

    /**
     * 根据使用次数key查询
     * @param usageCountKey
     * @return
     */
    default UsageCountRecordDO getByUsageCountKey(String usageCountKey) {
        Assert.notNull(usageCountKey,"usageCountKey 不能为空");
        return getOne(Wrappers.<UsageCountRecordDO>lambdaQuery().eq(UsageCountRecordDO::getUsageCountKey, usageCountKey));
    }



    /**
     * 根据使用次数key查询多个
     * @param usageCountKeys
     * @return
     */
    default List<UsageCountRecordDO> getByUsageCountKeys(List<String> usageCountKeys) {
        Assert.notEmpty(usageCountKeys,"usageCountKeys 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().in(UsageCountRecordDO::getUsageCountKey, usageCountKeys));
    }


    /**
     * 根据使用次数定义id查询
     * @param usageCountDefineId
     * @return
     */
    default List<UsageCountRecordDO> getByUsageCountDefineId(Long usageCountDefineId) {
        Assert.notNull(usageCountDefineId,"usageCountDefineId 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().eq(UsageCountRecordDO::getUsageCountDefineId, usageCountDefineId));
    }



    /**
     * 根据使用次数定义id查询多个
     * @param usageCountDefineIds
     * @return
     */
    default List<UsageCountRecordDO> getByUsageCountDefineIds(List<Long> usageCountDefineIds) {
        Assert.notEmpty(usageCountDefineIds,"usageCountDefineIds 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().in(UsageCountRecordDO::getUsageCountDefineId, usageCountDefineIds));
    }


    /**
     * 根据使用次数配置id查询
     * @param usageCountConfigId
     * @return
     */
    default List<UsageCountRecordDO> getByUsageCountConfigId(Long usageCountConfigId) {
        Assert.notNull(usageCountConfigId,"usageCountConfigId 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().eq(UsageCountRecordDO::getUsageCountConfigId, usageCountConfigId));
    }



    /**
     * 根据使用次数配置id查询多个
     * @param usageCountConfigIds
     * @return
     */
    default List<UsageCountRecordDO> getByUsageCountConfigIds(List<Long> usageCountConfigIds) {
        Assert.notEmpty(usageCountConfigIds,"usageCountConfigIds 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().in(UsageCountRecordDO::getUsageCountConfigId, usageCountConfigIds));
    }



    /**
     * 根据使用用户id查询
     * @param usageUserId
     * @return
     */
    default List<UsageCountRecordDO> getByUsageUserId(Long usageUserId) {
        Assert.notNull(usageUserId,"usageUserId 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().eq(UsageCountRecordDO::getUsageUserId, usageUserId));
    }



    /**
     * 根据使用用户id查询多个
     * @param usageUserIds
     * @return
     */
    default List<UsageCountRecordDO> getByUsageUserIds(List<Long> usageUserIds) {
        Assert.notEmpty(usageUserIds,"usageUserIds 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().in(UsageCountRecordDO::getUsageUserId, usageUserIds));
    }


    /**
     * 根据使用租户id查询
     * @param usageTenantId
     * @return
     */
    default List<UsageCountRecordDO> getByUsageTenantId(Long usageTenantId) {
        Assert.notNull(usageTenantId,"usageTenantId 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().eq(UsageCountRecordDO::getUsageTenantId, usageTenantId));
    }



    /**
     * 根据使用租户id查询多个
     * @param usageTenantIds
     * @return
     */
    default List<UsageCountRecordDO> getByUsageTenantIds(List<Long> usageTenantIds) {
        Assert.notEmpty(usageTenantIds,"usageTenantIds 不能为空");
        return list(Wrappers.<UsageCountRecordDO>lambdaQuery().in(UsageCountRecordDO::getUsageTenantId, usageTenantIds));
    }









}
