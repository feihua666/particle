package com.particle.scheduler.adapter.datatask.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.scheduler.client.datatask.api.ISchedulerJobDataTaskApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划任务数据前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Tag(name = "任务计划任务数据移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/scheduler_job_data_task")
public class SchedulerJobDataTaskFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ISchedulerJobDataTaskApplicationService iSchedulerJobDataTaskApplicationService;


}