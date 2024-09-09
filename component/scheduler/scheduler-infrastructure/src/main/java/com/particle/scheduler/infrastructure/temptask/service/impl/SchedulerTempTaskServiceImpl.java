package com.particle.scheduler.infrastructure.temptask.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.scheduler.infrastructure.temptask.dos.SchedulerTempTaskDO;
import com.particle.scheduler.infrastructure.temptask.mapper.SchedulerTempTaskMapper;
import com.particle.scheduler.infrastructure.temptask.service.ISchedulerTempTaskService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 任务计划临时任务 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Component
public class SchedulerTempTaskServiceImpl extends IBaseServiceImpl<SchedulerTempTaskMapper, SchedulerTempTaskDO> implements ISchedulerTempTaskService {
	private IBaseQueryCommandMapStruct<SchedulerTempTaskDO> queryCommandMapStruct;

	@Override
	protected SchedulerTempTaskDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SchedulerTempTaskDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SchedulerTempTaskDO po) {
	    // 临时任务编码 已存在不能添加
	    assertByColumn(po.getCode(),SchedulerTempTaskDO::getCode,false);

	}

	@Override
	protected void preUpdate(SchedulerTempTaskDO po) {
	    SchedulerTempTaskDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果临时任务编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 临时任务编码已存在不能修改
	            assertByColumn(po.getCode(),SchedulerTempTaskDO::getCode,false);
	        }
	    }

    
	}
}
