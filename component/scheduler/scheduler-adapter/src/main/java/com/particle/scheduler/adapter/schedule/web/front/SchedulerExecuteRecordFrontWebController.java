package com.particle.scheduler.adapter.schedule.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.scheduler.client.schedule.api.ISchedulerExecuteRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划执行记录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Tag(name = "任务计划执行记录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/scheduler_execute_record")
public class SchedulerExecuteRecordFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ISchedulerExecuteRecordApplicationService iSchedulerExecuteRecordApplicationService;


}