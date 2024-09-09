package com.particle.scheduler.adapter.temptask.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskRunRecordLogApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划临时任务运行记录日志前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Tag(name = "任务计划临时任务运行记录日志pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/scheduler_temp_task_run_record_log")
public class SchedulerTempTaskRunRecordLogFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ISchedulerTempTaskRunRecordLogApplicationService iSchedulerTempTaskRunRecordLogApplicationService;


}