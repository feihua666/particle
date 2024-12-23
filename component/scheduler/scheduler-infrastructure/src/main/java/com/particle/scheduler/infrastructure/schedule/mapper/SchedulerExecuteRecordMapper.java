package com.particle.scheduler.infrastructure.schedule.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 任务计划执行记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Mapper
public interface SchedulerExecuteRecordMapper extends IBaseMapper<SchedulerExecuteRecordDO> {

}
