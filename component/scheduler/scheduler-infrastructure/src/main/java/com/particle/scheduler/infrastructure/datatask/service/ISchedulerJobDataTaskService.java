package com.particle.scheduler.infrastructure.datatask.service;

import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 任务计划任务数据 服务类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
public interface ISchedulerJobDataTaskService extends IBaseService<SchedulerJobDataTaskDO> {


    /**
     * 根据唯一标识查询
     * @param uniqueIdentifier
     * @return
     */
    default SchedulerJobDataTaskDO getByUniqueIdentifier(String uniqueIdentifier) {
        Assert.notNull(uniqueIdentifier,"uniqueIdentifier 不能为空");
        return getOne(Wrappers.<SchedulerJobDataTaskDO>lambdaQuery().eq(SchedulerJobDataTaskDO::getUniqueIdentifier, uniqueIdentifier));
    }



    /**
     * 根据唯一标识查询多个
     * @param uniqueIdentifiers
     * @return
     */
    default List<SchedulerJobDataTaskDO> getByUniqueIdentifiers(List<String> uniqueIdentifiers) {
        Assert.notEmpty(uniqueIdentifiers,"uniqueIdentifiers 不能为空");
        return list(Wrappers.<SchedulerJobDataTaskDO>lambdaQuery().in(SchedulerJobDataTaskDO::getUniqueIdentifier, uniqueIdentifiers));
    }
            


















}
