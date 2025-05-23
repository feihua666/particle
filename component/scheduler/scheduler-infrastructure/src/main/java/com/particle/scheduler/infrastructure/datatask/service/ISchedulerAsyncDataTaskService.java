package com.particle.scheduler.infrastructure.datatask.service;

import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 任务计划异步任务数据 服务类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
public interface ISchedulerAsyncDataTaskService extends IBaseService<SchedulerAsyncDataTaskDO> {


    /**
     * 根据唯一标识查询
     * @param uniqueIdentifier
     * @return
     */
    default SchedulerAsyncDataTaskDO getByUniqueIdentifier(String uniqueIdentifier) {
        Assert.notNull(uniqueIdentifier,"uniqueIdentifier 不能为空");
        return getOne(Wrappers.<SchedulerAsyncDataTaskDO>lambdaQuery().eq(SchedulerAsyncDataTaskDO::getUniqueIdentifier, uniqueIdentifier));
    }



    /**
     * 根据唯一标识查询多个
     * @param uniqueIdentifiers
     * @return
     */
    default List<SchedulerAsyncDataTaskDO> getByUniqueIdentifiers(List<String> uniqueIdentifiers) {
        Assert.notEmpty(uniqueIdentifiers,"uniqueIdentifiers 不能为空");
        return list(Wrappers.<SchedulerAsyncDataTaskDO>lambdaQuery().in(SchedulerAsyncDataTaskDO::getUniqueIdentifier, uniqueIdentifiers));
    }
            


















}
