package com.particle.scheduler.infrastructure.datatask.mapper;

import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 任务计划异步任务数据 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Mapper
public interface SchedulerAsyncDataTaskMapper extends IBaseMapper<SchedulerAsyncDataTaskDO> {

}
