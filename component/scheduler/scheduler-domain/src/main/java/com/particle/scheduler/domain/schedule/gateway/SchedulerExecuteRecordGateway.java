package com.particle.scheduler.domain.schedule.gateway;

import com.particle.scheduler.domain.schedule.SchedulerExecuteRecord;
import com.particle.scheduler.domain.schedule.SchedulerExecuteRecordId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 任务计划执行记录 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
public interface SchedulerExecuteRecordGateway extends IBaseGateway<SchedulerExecuteRecordId,SchedulerExecuteRecord> {
}
