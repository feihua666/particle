package com.particle.scheduler.domain.temptask.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.scheduler.domain.temptask.SchedulerTempTask;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskId;

/**
 * <p>
 * 任务计划临时任务 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
public interface SchedulerTempTaskGateway extends IBaseGateway<SchedulerTempTaskId,SchedulerTempTask> {
}
