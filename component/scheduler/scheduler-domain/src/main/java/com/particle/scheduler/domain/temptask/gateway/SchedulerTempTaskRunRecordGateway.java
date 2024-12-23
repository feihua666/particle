package com.particle.scheduler.domain.temptask.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecord;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordId;

/**
 * <p>
 * 任务计划临时任务运行记录 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
public interface SchedulerTempTaskRunRecordGateway extends IBaseGateway<SchedulerTempTaskRunRecordId,SchedulerTempTaskRunRecord> {
}
