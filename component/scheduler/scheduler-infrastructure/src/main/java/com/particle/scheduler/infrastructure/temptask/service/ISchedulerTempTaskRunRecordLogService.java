package com.particle.scheduler.infrastructure.temptask.service;

import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 任务计划临时任务运行记录日志 服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
public interface ISchedulerTempTaskRunRecordLogService extends IBaseService<SchedulerTempTaskRunRecordLogDO> {

    /**
     * 根据临时任务运行记录id查询
     * @param schedulerTempTaskRunRecordId
     * @return
     */
    default List<SchedulerTempTaskRunRecordLogDO> getBySchedulerTempTaskRunRecordId(Long schedulerTempTaskRunRecordId) {
        Assert.notNull(schedulerTempTaskRunRecordId,"schedulerTempTaskRunRecordId 不能为空");
        return list(Wrappers.<SchedulerTempTaskRunRecordLogDO>lambdaQuery().eq(SchedulerTempTaskRunRecordLogDO::getSchedulerTempTaskRunRecordId, schedulerTempTaskRunRecordId));
    }



    /**
     * 根据临时任务运行记录id查询多个
     * @param schedulerTempTaskRunRecordIds
     * @return
     */
    default List<SchedulerTempTaskRunRecordLogDO> getBySchedulerTempTaskRunRecordIds(List<Long> schedulerTempTaskRunRecordIds) {
        Assert.notEmpty(schedulerTempTaskRunRecordIds,"schedulerTempTaskRunRecordIds 不能为空");
        return list(Wrappers.<SchedulerTempTaskRunRecordLogDO>lambdaQuery().in(SchedulerTempTaskRunRecordLogDO::getSchedulerTempTaskRunRecordId, schedulerTempTaskRunRecordIds));
    }
            










}
