package com.particle.scheduler.client.temptask.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划临时任务运行记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordVO extends AbstractBaseIdVO {

    @Schema(description = "临时任务id")
    private Long schedulerTempTaskId;

    @TransBy(tableName = TransTableNameConstants.component_scheduler_temp_task, byFieldName = "schedulerTempTaskId", mapValueField = "code")
    @Schema(description = "临时任务编码")
    private String schedulerTempTaskCode;

    @TransBy(tableName = TransTableNameConstants.component_scheduler_temp_task, byFieldName = "schedulerTempTaskId", mapValueField = "name")
    @Schema(description = "临时任务名称")
    private String schedulerTempTaskName;

    @Schema(description = "临时任务状态")
    private Long executeStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeStatusDictId",mapValueField = "name")
    @Schema(description = "临时任务状态对应字典名称")
    private String statusDictName;

    @Schema(description = "是否有异常")
    private Boolean isHasError;

    @Schema(description = "是否允许运行开关")
    private Boolean isAllowRunSwitch;

    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;

    @Schema(description = "本地主机ip")
    private String localHostIp;

    @Schema(description = "本地主机名称")
    private String localHostName;

	@Schema(description = "日志追踪id")
	private String traceId;

	@Schema(description = "运行结果，运行成果物")
	private String result;



}
