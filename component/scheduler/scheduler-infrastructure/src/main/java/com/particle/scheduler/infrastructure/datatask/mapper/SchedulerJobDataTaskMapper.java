package com.particle.scheduler.infrastructure.datatask.mapper;

import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 任务计划任务数据 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Mapper
public interface SchedulerJobDataTaskMapper extends IBaseMapper<SchedulerJobDataTaskDO> {

}
