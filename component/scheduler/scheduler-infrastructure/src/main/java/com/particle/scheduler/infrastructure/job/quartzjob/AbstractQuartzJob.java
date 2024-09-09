package com.particle.scheduler.infrastructure.job.quartzjob;

import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.json.JsonTool;
import com.particle.scheduler.domain.enums.SchedulerRecordExecuteStatus;
import com.particle.scheduler.domain.gateway.SchedulerDictGateway;
import com.particle.scheduler.domain.value.NameAndGroupParam;
import com.particle.scheduler.infrastructure.schedule.service.ISchedulerExecuteRecordService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 抽象任务封装
 * </p>
 *
 * @author yangwei
 * @since 2021-10-11 14:18
 */
@Slf4j
public abstract class AbstractQuartzJob implements Job {

	@Autowired
	private ISchedulerExecuteRecordService iSchedulerRecordService;
	@Autowired
	private SchedulerDictGateway schedulerDictGateway;


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("任务执行开始,name={},group={},mergedJobDataMap={}",
				context.getJobDetail().getKey().getName(),
				context.getJobDetail().getKey().getGroup(),
				JsonTool.toJsonStr(context.getMergedJobDataMap()));
		log.info("任务执行 mergedJobDataMap={}",context.getMergedJobDataMap().toString());

		Long recordId = null;
		Long executeStatusDictId = null;
		String result = null;
		try {

			NameAndGroupParam nameAndGroupParam = new NameAndGroupParam();
			nameAndGroupParam.setName(context.getJobDetail().getKey().getName());
			nameAndGroupParam.setGroup(context.getJobDetail().getKey().getGroup());
			boolean b = iSchedulerRecordService.existRunning(nameAndGroupParam);
			if (b) {
				log.info("任务执行结束，任务尚未执行，因为上一次任务还未结束,name={},group={}",context.getJobDetail().getKey().getName(),context.getJobDetail().getKey().getGroup());
				iSchedulerRecordService.jobConflictOverRecord(context,"任务尚未执行，因为上一次任务还未结束");
				return;
			}
			recordId = iSchedulerRecordService.addStartJobRecord(context);

			result = doExecute(context);
			executeStatusDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerRecordExecuteStatus.Group.scheduler_record_execute_status.groupCode(),
					SchedulerRecordExecuteStatus.success.itemValue());

		}catch (Exception e){
			log.error("任务执行异常",e);
			executeStatusDictId = schedulerDictGateway.getDictIdByGroupCodeAndItemValue(SchedulerRecordExecuteStatus.Group.scheduler_record_execute_status.groupCode(),
					SchedulerRecordExecuteStatus.fail.itemValue());
			if (StrUtil.isEmpty(result)) {

				if (e instanceof HttpServerErrorException) {

					HttpServerErrorException httpServerErrorException= ((HttpServerErrorException) e);
					HttpResult httpResult = httpResultWrap(httpServerErrorException.getRawStatusCode(), httpServerErrorException.getResponseBodyAsString(), httpServerErrorException.getResponseHeaders());
					result = JsonTool.toJsonStr(httpResult);

				}else if(e instanceof HttpClientErrorException){
					HttpClientErrorException httpClientErrorException = ((HttpClientErrorException)e);
					HttpResult httpResult = httpResultWrap(httpClientErrorException.getRawStatusCode(), httpClientErrorException.getResponseBodyAsString(), httpClientErrorException.getResponseHeaders());
					result = JsonTool.toJsonStr(httpResult);
				}
			}

		}
		finally {
			log.info("任务执行结束,name={},group={}",context.getJobDetail().getKey().getName(),context.getJobDetail().getKey().getGroup());
			if (recordId == null || executeStatusDictId == null) {
				log.error("这是由于记录执行日志而引起的错误，这很有可能是一个bug,recordId={},executeStatusDictId={}",recordId,executeStatusDictId);
			}else {
				iSchedulerRecordService.endJobUpdateRecord(recordId,result,executeStatusDictId);
			}
		}
	}

	/**
	 * 任务执行
	 * @param context
	 * @return 返回任务执行结果
	 * @throws JobExecutionException
	 */
	public abstract String doExecute(JobExecutionContext context) throws JobExecutionException;

	/**
	 * http 调用响应结果封装
	 * @param httpStatus
	 * @param body
	 * @param httpHeaders
	 * @return
	 */
	public HttpResult httpResultWrap(int httpStatus, String body, HttpHeaders httpHeaders){
		HttpResult httpResult = new HttpResult();
		httpResult.setStatus(httpStatus);
		httpResult.setBody(body);

		Map<String, Object> responseHeaders = new HashMap<>();
		for (String key : httpHeaders.keySet()) {
			List<String> list = httpHeaders.get(key);
			responseHeaders.put(key, list);
		}
		httpResult.setHeaders(responseHeaders);

		return httpResult;

	}

	@Setter
	@Getter
	public static class HttpResult{
		private int status;
		private String body;
		private Map<String, Object> headers;
	}
}
