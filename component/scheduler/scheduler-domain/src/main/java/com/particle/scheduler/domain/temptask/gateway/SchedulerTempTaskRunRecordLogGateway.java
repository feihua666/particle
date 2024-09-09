package com.particle.scheduler.domain.temptask.gateway;

import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLog;
import com.particle.scheduler.domain.temptask.SchedulerTempTaskRunRecordLogId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 任务计划临时任务运行记录日志 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
public interface SchedulerTempTaskRunRecordLogGateway extends IBaseGateway<SchedulerTempTaskRunRecordLogId,SchedulerTempTaskRunRecordLog> {
}
