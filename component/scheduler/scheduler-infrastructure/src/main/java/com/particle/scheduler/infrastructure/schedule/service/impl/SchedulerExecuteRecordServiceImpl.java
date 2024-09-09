package com.particle.scheduler.infrastructure.schedule.service.impl;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.log.TraceTool;
import com.particle.scheduler.domain.enums.SchedulerRecordExecuteStatus;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.domain.value.NameAndGroupParam;
import com.particle.scheduler.infrastructure.schedule.dos.SchedulerExecuteRecordDO;
import com.particle.scheduler.infrastructure.schedule.mapper.SchedulerExecuteRecordMapper;
import com.particle.scheduler.infrastructure.schedule.service.ISchedulerExecuteRecordService;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划执行记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Component
public class SchedulerExecuteRecordServiceImpl extends IBaseServiceImpl<SchedulerExecuteRecordMapper, SchedulerExecuteRecordDO> implements ISchedulerExecuteRecordService {
	private IBaseQueryCommandMapStruct<SchedulerExecuteRecordDO> queryCommandMapStruct;

	private SchedulerDictGateway schedulerDictGateway;
	@Override
	protected SchedulerExecuteRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SchedulerExecuteRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SchedulerExecuteRecordDO po) {
	    // 任务名称 已存在不能添加
	    assertByColumn(po.getName(),SchedulerExecuteRecordDO::getName,false);

	    // 任务组 已存在不能添加
	    assertByColumn(po.getGroupName(),SchedulerExecuteRecordDO::getGroupName,false);

	}

	@Override
	protected void preUpdate(SchedulerExecuteRecordDO po) {
	    SchedulerExecuteRecordDO byId = null;
	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果任务名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 任务名称已存在不能修改
	            assertByColumn(po.getName(),SchedulerExecuteRecordDO::getName,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getGroupName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果任务组有改动
	        if (!po.getGroupName().equals(byId.getGroupName())) {
	            // 任务组已存在不能修改
	            assertByColumn(po.getGroupName(),SchedulerExecuteRecordDO::getGroupName,false);
	        }
	    }

    
	}

	@Override
	public boolean existRunning(NameAndGroupParam nameAndGroupForm) {
		SchedulerExecuteRecordDO schedulerRecord = new SchedulerExecuteRecordDO();
		schedulerRecord.setName(nameAndGroupForm.getName());
		schedulerRecord.setGroupName(nameAndGroupForm.getGroup());
		Long runningDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerRecordExecuteStatus.Group.scheduler_record_execute_status.groupCode(),
				SchedulerRecordExecuteStatus.running.itemValue());
		schedulerRecord.setExecuteStatusDictId(runningDictId);
		return count(Wrappers.query(schedulerRecord)) > 0;
	}

	@Override
	public Long addStartJobRecord(JobExecutionContext context) throws SchedulerException {
		SchedulerExecuteRecordDO schedulerRecord = mapRecordByJobExecutionContext(context);

		Long runningDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerRecordExecuteStatus.Group.scheduler_record_execute_status.groupCode(),
				SchedulerRecordExecuteStatus.running.itemValue());
		schedulerRecord.setExecuteStatusDictId(runningDictId);

		boolean save = save(schedulerRecord);
		return schedulerRecord.getId();
	}

	@Override
	public boolean endJobUpdateRecord(Long recordId, String result, Long statusDictId) {
		SchedulerExecuteRecordDO schedulerRecord = new SchedulerExecuteRecordDO();

		schedulerRecord.setId(recordId);
		schedulerRecord.setResult(result);
		schedulerRecord.setFinishAt(LocalDateTime.now());
		schedulerRecord.setExecuteStatusDictId(statusDictId);
		updateById(schedulerRecord);
		return true;
	}

	@Override
	public boolean jobConflictOverRecord(JobExecutionContext context, String result) throws SchedulerException {
		Long recordId = addStartJobRecord(context);
		Long failDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerRecordExecuteStatus.Group.scheduler_record_execute_status.groupCode(),
				SchedulerRecordExecuteStatus.fail.itemValue());

		boolean b = endJobUpdateRecord(recordId, result, failDictId);
		return b;
	}
	/**
	 * 映射实体
	 * @param context
	 * @return
	 * @throws SchedulerException
	 */
	private SchedulerExecuteRecordDO mapRecordByJobExecutionContext(JobExecutionContext context) throws SchedulerException {
		SchedulerExecuteRecordDO schedulerRecord = new SchedulerExecuteRecordDO();
		schedulerRecord.setSchedulerName(context.getScheduler().getSchedulerName());
		schedulerRecord.setSchedulerInstanceId(context.getScheduler().getSchedulerInstanceId());
		schedulerRecord.setName(context.getJobDetail().getKey().getName());
		schedulerRecord.setGroupName(context.getJobDetail().getKey().getGroup());
		schedulerRecord.setStartAt(LocalDateTime.now());
		schedulerRecord.setParams(JsonTool.toJsonStr(context.getJobDetail().getJobDataMap()));

		schedulerRecord.setTraceId(TraceTool.getTraceId());

		schedulerRecord.setLocalHostIp(NetUtil.getLocalhostStr());
		schedulerRecord.setLocalHostName(NetUtil.getLocalHostName());

		return schedulerRecord;
	}

	@Autowired
	public void setSchedulerDictGateway(SchedulerDictGateway schedulerDictGateway) {
		this.schedulerDictGateway = schedulerDictGateway;
	}


}
