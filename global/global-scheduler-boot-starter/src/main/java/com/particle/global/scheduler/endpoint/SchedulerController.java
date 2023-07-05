package com.particle.global.scheduler.endpoint;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import com.particle.global.dto.basic.Command;
import com.particle.global.dto.basic.VO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.scheduler.IGlobalScheduler;
import com.particle.global.scheduler.SchedulerParamTool;
import com.particle.global.scheduler.config.GlobalSchedulingConfigurer;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.tool.json.JsonTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 文档相关服务接口
 * </p>
 *
 * @author yangwei
 * @since 2023-07-05 14:56:00
 */
@Api(tags = "全局服务任务计划相关接口")
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

	@Autowired(required = false)
	private Map<String, IGlobalScheduler> globalSchedulerMap;
	@Autowired
	private GlobalSchedulingConfigurer globalSchedulingConfigurer;

	/**
	 * 手动执行任务计划是一个危险操作，只有超级管理员才能使用
	 * 注意：手动执行任务计划和自动触发是完全两个触发方式，也就是说该触发不影响原始任务计划的触发逻辑
	 * @param param
	 * @throws Throwable
	 */
	@PreAuthorize("hasRole('"+ LoginUser.super_admin_role +"')")
	@ApiOperation("手动执行任务计划")
	@PostMapping( "/manualExecution")
	public Response manualExecution(@RequestBody String param,@Valid SchedulerManualExeCommand schedulerManualExeCommand) throws Throwable {
		if (globalSchedulerMap == null) {
			return Response.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND, "can not find scheduler or you must implement IGlobalScheduler interface");
		}
		IGlobalScheduler iGlobalScheduler = globalSchedulerMap.get(schedulerManualExeCommand.getSchedulerName());
		Assert.notNull(iGlobalScheduler,"schedulerName is not exist");
		Method methodByName = ReflectUtil.getMethodByName(iGlobalScheduler.getClass(), schedulerManualExeCommand.getSchedulerMethodName());
		Assert.notNull(methodByName,"schedulerMethodName is not exist");

		try {
			SchedulerParamTool.set(param);
			ReflectUtil.invoke(iGlobalScheduler, methodByName);
		} finally {
			SchedulerParamTool.clear();
		}

		return Response.buildSuccess();
	}

	@PreAuthorize("hasAuthority('user')")
	@ApiOperation("任务计划列表")
	@GetMapping( "/list")
	public MultiResponse<SchedulerVO> list() throws Throwable {
		if (globalSchedulerMap == null) {
			return MultiResponse.buildSuccess();
		}
		List<SchedulerVO> result = new ArrayList<>();
		SchedulerVO schedulerVO = null;
		for (Map.Entry<String, IGlobalScheduler> stringIGlobalSchedulerEntry : globalSchedulerMap.entrySet()) {
			IGlobalScheduler value = stringIGlobalSchedulerEntry.getValue();
			value = (IGlobalScheduler)AopProxyUtils.getSingletonTarget(value);
			Method[] methods = ReflectUtil.getMethods(value.getClass());
			for (Method method : methods) {
				Scheduled scheduled = AnnotationUtil.getAnnotation(method, Scheduled.class);
				if (scheduled != null) {
					schedulerVO = new SchedulerVO();
					schedulerVO.setSchedulerName(stringIGlobalSchedulerEntry.getKey());
					schedulerVO.setSchedulerMethodName(method.getName());
					schedulerVO.setAnnotationName("@Scheduled");
					ScheduledJsonDTO scheduledJsonDTO = ScheduledJsonDTO.create(scheduled);
					String json = JsonTool.toJsonStr(scheduledJsonDTO);
					schedulerVO.setAnnotationParams(json);
					result.add(schedulerVO);
				}

			}

		}

		return MultiResponse.of(result);
	}


	@Data
	@ApiModel
	public static class SchedulerManualExeCommand extends Command {

		@NotEmpty(message = "任务计划名称 不能为空")
		@ApiModelProperty(value = "任务计划名称，bean名称",required = true)
		private String schedulerName;

		@NotEmpty(message = "任务计划调用方法名称 不能为空")
		@ApiModelProperty(value = "任务计划调用方法名称",required = true)
		private String schedulerMethodName;
	}

	@Data
	@ApiModel
	public static class SchedulerVO extends VO {

		@ApiModelProperty(value = "任务计划名称，bean名称")
		private String schedulerName;

		@ApiModelProperty(value = "任务计划调用方法名称")
		private String schedulerMethodName;

		@ApiModelProperty(value = "注解名称")
		private String annotationName;

		@ApiModelProperty(value = "注解参数，仅供肉眼识别文本")
		private String annotationParams;

	}

	@Data
	public static class ScheduledJsonDTO {

		private String cron;
		private String zone;
		private long fixedDelay;
		private String fixedDelayString;
		private long fixedRate;
		private String fixedRateString;
		private long initialDelay;
		private String initialDelayString;
		private TimeUnit timeUnit;

		public static ScheduledJsonDTO create(Scheduled scheduled) {
			ScheduledJsonDTO scheduledJsonDTO = new ScheduledJsonDTO();
			scheduledJsonDTO.cron = scheduled.cron();
			scheduledJsonDTO.zone = scheduled.zone();
			scheduledJsonDTO.fixedDelay = scheduled.fixedDelay();
			scheduledJsonDTO.fixedDelayString = scheduled.fixedDelayString();
			scheduledJsonDTO.fixedRate = scheduled.fixedRate();
			scheduledJsonDTO.fixedRateString = scheduled.fixedRateString();
			scheduledJsonDTO.initialDelay = scheduled.initialDelay();
			scheduledJsonDTO.initialDelayString = scheduled.initialDelayString();
			scheduledJsonDTO.timeUnit = scheduled.timeUnit();
			return scheduledJsonDTO;
		}
	}

}