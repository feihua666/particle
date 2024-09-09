package com.particle.scheduler.adapter.schedule.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.scheduler.client.schedule.api.ISchedulerExecuteRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划执行记录后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Tag(name = "任务计划执行记录wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/scheduler_execute_record")
public class SchedulerExecuteRecordAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ISchedulerExecuteRecordApplicationService iSchedulerExecuteRecordApplicationService;


}