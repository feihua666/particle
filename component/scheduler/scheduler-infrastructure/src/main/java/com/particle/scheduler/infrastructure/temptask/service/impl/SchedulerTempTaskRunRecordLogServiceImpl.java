package com.particle.scheduler.infrastructure.temptask.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordLogDO;
import com.particle.scheduler.infrastructure.temptask.mapper.SchedulerTempTaskRunRecordLogMapper;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordLogService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 任务计划临时任务运行记录日志 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Component
public class SchedulerTempTaskRunRecordLogServiceImpl extends IBaseServiceImpl<SchedulerTempTaskRunRecordLogMapper, SchedulerTempTaskRunRecordLogDO> implements ISchedulerTempTaskRunRecordLogService {
	private IBaseQueryCommandMapStruct<SchedulerTempTaskRunRecordLogDO> queryCommandMapStruct;

	@Override
	protected SchedulerTempTaskRunRecordLogDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SchedulerTempTaskRunRecordLogDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SchedulerTempTaskRunRecordLogDO po) {
	}

	@Override
	protected void preUpdate(SchedulerTempTaskRunRecordLogDO po) {
    
	}
}
