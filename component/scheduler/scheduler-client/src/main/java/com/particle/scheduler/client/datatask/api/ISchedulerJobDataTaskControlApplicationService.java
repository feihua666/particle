package com.particle.scheduler.client.datatask.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.scheduler.client.datatask.dto.data.SchedulerDataTaskVO;
import com.particle.scheduler.client.datatask.dto.data.SchedulerJobDataTaskControlVO;

import java.time.LocalDateTime;

/**
 * <p>
 * 任务计划数据任务 控制应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-05-22 22:38:42
 */
public interface ISchedulerJobDataTaskControlApplicationService extends IBaseApplicationService {
	/**
	 * 提交一个任务
	 * 该方法只会记录任务状态，不会执行任务逻辑
	 * @param groupIdentifier 如：异步指标计算
	 * @param uniqueIdentifier 唯一标识符，如：一个uuid
	 * @param params 参数信息
	 * @param dataExpireAt 数据过期时间，过期后，代表数据无用，可以随时被清理
	 * @return 返回任务运行记录的id
	 */
	SchedulerJobDataTaskControlVO submit(String groupIdentifier, String uniqueIdentifier, String params, LocalDateTime dataExpireAt);
	/**
	 * 提交一个任务，返回视图不同
	 * @param groupIdentifier
	 * @param uniqueIdentifier
	 * @param params
	 * @param dataExpireAt
	 * @return
	 */
	SchedulerDataTaskVO submitAndGetDataTaskVO(String groupIdentifier, String uniqueIdentifier, String params, LocalDateTime dataExpireAt);

	/**
     * 开始处理任务
	 * @param id
     * @param uniqueIdentifier
	 */
	void processStart(Long id, String uniqueIdentifier);
	/**
	 * 完成任务
	 * @param id 任务id,唯一标识该任务 唯一标识该任务 id 和 uniqueIdentifier 至少传一个
	 * @param uniqueIdentifier 唯一标识该任务 id 和 uniqueIdentifier 至少传一个
	 * @param isHasError 是否有异常，如果有异常，异常的内容应该使用 {@link ISchedulerJobDataTaskControlApplicationService#log(String, Long, String)}记录
	 * @param errorMessage 有异常时提示信息
	 * @param result 执行完成后的结果
	 */
	void finish(Long id,String uniqueIdentifier,Boolean isHasError,String errorMessage,String result);

	/**
	 * 获取任务信息
	 * @param id
	 * @param uniqueIdentifier
	 * @return
	 */
	SchedulerJobDataTaskControlVO getControlVO(Long id, String uniqueIdentifier);

	/**
	 * 获取任务信息，可响应数据
	 * @param id
	 * @param uniqueIdentifier
	 * @param convertResultToMap
	 * @return
	 */
	SchedulerDataTaskVO getDataTaskVO(Long id, String uniqueIdentifier, boolean convertResultToMap);
}
