package com.particle.scheduler.infrastructure.datatask.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerJobDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.mapper.SchedulerJobDataTaskMapper;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerJobDataTaskService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 任务计划任务数据 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Component
public class SchedulerJobDataTaskServiceImpl extends IBaseServiceImpl<SchedulerJobDataTaskMapper, SchedulerJobDataTaskDO> implements ISchedulerJobDataTaskService {
	private IBaseQueryCommandMapStruct<SchedulerJobDataTaskDO> queryCommandMapStruct;

	@Override
	protected SchedulerJobDataTaskDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SchedulerJobDataTaskDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SchedulerJobDataTaskDO po) {
	    // 唯一标识 已存在不能添加
	    assertByColumn(po.getUniqueIdentifier(),SchedulerJobDataTaskDO::getUniqueIdentifier,false);

	}

	@Override
	protected void preUpdate(SchedulerJobDataTaskDO po) {
	    SchedulerJobDataTaskDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUniqueIdentifier())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果唯一标识有改动
	        if (!po.getUniqueIdentifier().equals(byId.getUniqueIdentifier())) {
	            // 唯一标识已存在不能修改
	            assertByColumn(po.getUniqueIdentifier(),SchedulerJobDataTaskDO::getUniqueIdentifier,false);
	        }
	    }

    
	}
}
