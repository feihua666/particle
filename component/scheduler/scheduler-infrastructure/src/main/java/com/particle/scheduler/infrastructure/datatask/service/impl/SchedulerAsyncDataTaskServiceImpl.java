package com.particle.scheduler.infrastructure.datatask.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.scheduler.infrastructure.datatask.dos.SchedulerAsyncDataTaskDO;
import com.particle.scheduler.infrastructure.datatask.mapper.SchedulerAsyncDataTaskMapper;
import com.particle.scheduler.infrastructure.datatask.service.ISchedulerAsyncDataTaskService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 任务计划异步任务数据 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Component
public class SchedulerAsyncDataTaskServiceImpl extends IBaseServiceImpl<SchedulerAsyncDataTaskMapper, SchedulerAsyncDataTaskDO> implements ISchedulerAsyncDataTaskService {
	private IBaseQueryCommandMapStruct<SchedulerAsyncDataTaskDO> queryCommandMapStruct;

	@Override
	protected SchedulerAsyncDataTaskDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SchedulerAsyncDataTaskDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SchedulerAsyncDataTaskDO po) {
	    // 唯一标识 已存在不能添加
	    assertByColumn(po.getUniqueIdentifier(),SchedulerAsyncDataTaskDO::getUniqueIdentifier,false);

	}

	@Override
	protected void preUpdate(SchedulerAsyncDataTaskDO po) {
	    SchedulerAsyncDataTaskDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUniqueIdentifier())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果唯一标识有改动
	        if (!po.getUniqueIdentifier().equals(byId.getUniqueIdentifier())) {
	            // 唯一标识已存在不能修改
	            assertByColumn(po.getUniqueIdentifier(),SchedulerAsyncDataTaskDO::getUniqueIdentifier,false);
	        }
	    }

    
	}
}
