package com.particle.scheduler.adapter.datatask.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划异步任务数据后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Tag(name = "任务计划异步任务数据移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/scheduler_async_data_task")
public class SchedulerAsyncDataTaskAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ISchedulerAsyncDataTaskApplicationService iSchedulerAsyncDataTaskApplicationService;


}