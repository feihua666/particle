package com.particle.scheduler.infrastructure.temptask.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 任务计划临时任务运行记录 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Mapper
public interface SchedulerTempTaskRunRecordMapper extends IBaseMapper<SchedulerTempTaskRunRecordDO> {

}
