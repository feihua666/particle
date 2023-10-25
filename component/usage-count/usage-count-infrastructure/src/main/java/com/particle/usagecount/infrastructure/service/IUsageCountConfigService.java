package com.particle.usagecount.infrastructure.service;

import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 使用次数配置 服务类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
public interface IUsageCountConfigService extends IBaseService<UsageCountConfigDO> {


    /**
     * 根据使用次数定义id查询
     * @param usageCountDefineId
     * @return
     */
    default List<UsageCountConfigDO> getByUsageCountDefineId(Long usageCountDefineId) {
        Assert.notNull(usageCountDefineId,"usageCountDefineId 不能为空");
        return list(Wrappers.<UsageCountConfigDO>lambdaQuery().eq(UsageCountConfigDO::getUsageCountDefineId, usageCountDefineId));
    }



    /**
     * 根据使用次数定义id查询多个
     * @param usageCountDefineIds
     * @return
     */
    default List<UsageCountConfigDO> getByUsageCountDefineIds(List<Long> usageCountDefineIds) {
        Assert.notEmpty(usageCountDefineIds,"usageCountDefineIds 不能为空");
        return list(Wrappers.<UsageCountConfigDO>lambdaQuery().in(UsageCountConfigDO::getUsageCountDefineId, usageCountDefineIds));
    }
            












}
