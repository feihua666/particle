package com.particle.scheduler.adapter.temptask.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.scheduler.client.temptask.api.ISchedulerTempTaskApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务计划临时任务后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Tag(name = "任务计划临时任务wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/scheduler_temp_task")
public class SchedulerTempTaskAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ISchedulerTempTaskApplicationService iSchedulerTempTaskApplicationService;


}