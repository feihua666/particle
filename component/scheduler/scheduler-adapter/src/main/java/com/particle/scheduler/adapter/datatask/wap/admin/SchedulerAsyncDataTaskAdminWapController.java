package com.particle.scheduler.adapter.datatask.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划异步任务数据后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Tag(name = "任务计划异步任务数据wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/scheduler_async_data_task")
public class SchedulerAsyncDataTaskAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ISchedulerAsyncDataTaskApplicationService iSchedulerAsyncDataTaskApplicationService;


}