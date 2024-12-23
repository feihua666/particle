package com.particle.scheduler.infrastructure.temptask.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;

import java.util.List;

/**
 * <p>
 * 任务计划临时任务运行记录 服务类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
public interface ISchedulerTempTaskRunRecordService extends IBaseService<SchedulerTempTaskRunRecordDO> {

    /**
     * 根据临时任务id查询
     * @param schedulerTempTaskId
     * @return
     */
    default List<SchedulerTempTaskRunRecordDO> getBySchedulerTempTaskId(Long schedulerTempTaskId) {
        Assert.notNull(schedulerTempTaskId,"schedulerTempTaskId 不能为空");
        return list(Wrappers.<SchedulerTempTaskRunRecordDO>lambdaQuery().eq(SchedulerTempTaskRunRecordDO::getSchedulerTempTaskId, schedulerTempTaskId));
    }

    /**
     * 根据临时任务id查询
     * @param schedulerTempTaskId
     * @return
     */
    default List<SchedulerTempTaskRunRecordDO> getBySchedulerTempTaskIdAndStatusDictId(Long schedulerTempTaskId, Long executeStatusDictId) {
        Assert.notNull(schedulerTempTaskId,"schedulerTempTaskId 不能为空");
        return list(Wrappers.<SchedulerTempTaskRunRecordDO>lambdaQuery().eq(SchedulerTempTaskRunRecordDO::getSchedulerTempTaskId, schedulerTempTaskId)
                .eq(executeStatusDictId != null,SchedulerTempTaskRunRecordDO::getExecuteStatusDictId, executeStatusDictId));
    }

    /**
     * 根据临时任务id查询多个
     * @param schedulerTempTaskIds
     * @return
     */
    default List<SchedulerTempTaskRunRecordDO> getBySchedulerTempTaskIds(List<Long> schedulerTempTaskIds) {
        Assert.notEmpty(schedulerTempTaskIds,"schedulerTempTaskIds 不能为空");
        return list(Wrappers.<SchedulerTempTaskRunRecordDO>lambdaQuery().in(SchedulerTempTaskRunRecordDO::getSchedulerTempTaskId, schedulerTempTaskIds));
    }
















}
