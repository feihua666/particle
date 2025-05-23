package com.particle.scheduler.domain.datatask.gateway;

import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTask;
import com.particle.scheduler.domain.datatask.SchedulerAsyncDataTaskId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 任务计划异步任务数据 防腐层
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
public interface SchedulerAsyncDataTaskGateway extends IBaseGateway<SchedulerAsyncDataTaskId,SchedulerAsyncDataTask> {

    SchedulerAsyncDataTask getByUniqueIdentifier(String uniqueIdentifier);
}
