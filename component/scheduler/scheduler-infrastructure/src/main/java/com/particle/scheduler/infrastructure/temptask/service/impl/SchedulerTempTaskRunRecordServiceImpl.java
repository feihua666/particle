package com.particle.scheduler.infrastructure.temptask.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskRunRecordDO;
import com.particle.scheduler.infrastructure.temptask.mapper.SchedulerTempTaskRunRecordMapper;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskRunRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 任务计划临时任务运行记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Component
public class SchedulerTempTaskRunRecordServiceImpl extends IBaseServiceImpl<SchedulerTempTaskRunRecordMapper, SchedulerTempTaskRunRecordDO> implements ISchedulerTempTaskRunRecordService {
	private IBaseQueryCommandMapStruct<SchedulerTempTaskRunRecordDO> queryCommandMapStruct;

	@Override
	protected SchedulerTempTaskRunRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SchedulerTempTaskRunRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SchedulerTempTaskRunRecordDO po) {
	}

	@Override
	protected void preUpdate(SchedulerTempTaskRunRecordDO po) {

	}
}
