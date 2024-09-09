package com.particle.scheduler.adapter.schedule.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.scheduler.client.schedule.api.ISchedulerExecuteRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划执行记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Tag(name = "任务计划执行记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/scheduler_execute_record")
public class SchedulerExecuteRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ISchedulerExecuteRecordApplicationService iSchedulerExecuteRecordApplicationService;


}