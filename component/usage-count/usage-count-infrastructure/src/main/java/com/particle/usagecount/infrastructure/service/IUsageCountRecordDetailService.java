package com.particle.usagecount.infrastructure.service;

import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 使用次数记录明细 服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
public interface IUsageCountRecordDetailService extends IBaseService<UsageCountRecordDetailDO> {

    /**
     * 根据使用次数记录id查询
     * @param usageCountRecordId
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageCountRecordId(Long usageCountRecordId) {
        Assert.notNull(usageCountRecordId,"usageCountRecordId 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().eq(UsageCountRecordDetailDO::getUsageCountRecordId, usageCountRecordId));
    }



    /**
     * 根据使用次数记录id查询多个
     * @param usageCountRecordIds
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageCountRecordIds(List<Long> usageCountRecordIds) {
        Assert.notEmpty(usageCountRecordIds,"usageCountRecordIds 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().in(UsageCountRecordDetailDO::getUsageCountRecordId, usageCountRecordIds));
    }
            

    /**
     * 根据使用次数定义id查询
     * @param usageCountDefineId
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageCountDefineId(Long usageCountDefineId) {
        Assert.notNull(usageCountDefineId,"usageCountDefineId 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().eq(UsageCountRecordDetailDO::getUsageCountDefineId, usageCountDefineId));
    }



    /**
     * 根据使用次数定义id查询多个
     * @param usageCountDefineIds
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageCountDefineIds(List<Long> usageCountDefineIds) {
        Assert.notEmpty(usageCountDefineIds,"usageCountDefineIds 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().in(UsageCountRecordDetailDO::getUsageCountDefineId, usageCountDefineIds));
    }
            

    /**
     * 根据使用用户id查询
     * @param usageUserId
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageUserId(Long usageUserId) {
        Assert.notNull(usageUserId,"usageUserId 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().eq(UsageCountRecordDetailDO::getUsageUserId, usageUserId));
    }



    /**
     * 根据使用用户id查询多个
     * @param usageUserIds
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageUserIds(List<Long> usageUserIds) {
        Assert.notEmpty(usageUserIds,"usageUserIds 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().in(UsageCountRecordDetailDO::getUsageUserId, usageUserIds));
    }
            

    /**
     * 根据使用租户id查询
     * @param usageTenantId
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageTenantId(Long usageTenantId) {
        Assert.notNull(usageTenantId,"usageTenantId 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().eq(UsageCountRecordDetailDO::getUsageTenantId, usageTenantId));
    }



    /**
     * 根据使用租户id查询多个
     * @param usageTenantIds
     * @return
     */
    default List<UsageCountRecordDetailDO> getByUsageTenantIds(List<Long> usageTenantIds) {
        Assert.notEmpty(usageTenantIds,"usageTenantIds 不能为空");
        return list(Wrappers.<UsageCountRecordDetailDO>lambdaQuery().in(UsageCountRecordDetailDO::getUsageTenantId, usageTenantIds));
    }
            








}
