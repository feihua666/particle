package com.particle.scheduler.adapter.temptask.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskRunRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划临时任务运行记录前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Tag(name = "任务计划临时任务运行记录移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/scheduler_temp_task_run_record")
public class SchedulerTempTaskRunRecordFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ISchedulerTempTaskRunRecordApplicationService iSchedulerTempTaskRunRecordApplicationService;


}