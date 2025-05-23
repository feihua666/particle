package com.particle.scheduler.adapter.datatask.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.scheduler.client.datatask.api.ISchedulerAsyncDataTaskApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划异步任务数据前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Tag(name = "任务计划异步任务数据移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/scheduler_async_data_task")
public class SchedulerAsyncDataTaskFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ISchedulerAsyncDataTaskApplicationService iSchedulerAsyncDataTaskApplicationService;


}