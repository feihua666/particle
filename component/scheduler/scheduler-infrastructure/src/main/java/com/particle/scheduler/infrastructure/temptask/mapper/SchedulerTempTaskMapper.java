package com.particle.scheduler.infrastructure.temptask.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 任务计划临时任务 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Mapper
public interface SchedulerTempTaskMapper extends IBaseMapper<SchedulerTempTaskDO> {

}
