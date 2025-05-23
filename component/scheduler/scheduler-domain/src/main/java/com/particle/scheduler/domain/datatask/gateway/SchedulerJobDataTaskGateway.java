package com.particle.scheduler.domain.datatask.gateway;

import com.particle.scheduler.domain.datatask.SchedulerJobDataTask;
import com.particle.scheduler.domain.datatask.SchedulerJobDataTaskId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 任务计划任务数据 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
public interface SchedulerJobDataTaskGateway extends IBaseGateway<SchedulerJobDataTaskId,SchedulerJobDataTask> {
}
