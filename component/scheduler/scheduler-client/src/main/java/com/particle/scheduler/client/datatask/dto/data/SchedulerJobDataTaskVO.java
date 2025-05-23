package com.particle.scheduler.client.datatask.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划任务数据 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-05-22 17:33:46
 */
@Data
@Schema
public class SchedulerJobDataTaskVO extends AbstractBaseIdVO {

    @Schema(description = "任务分组标识")
    private String groupIdentifier;

    @Schema(description = "唯一标识")
    private String uniqueIdentifier;

    @Schema(description = "执行参数")
    private String params;

    @Schema(description = "执行状态")
    private Long executeStatusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeStatusDictId",mapValueField = "value")
    @Schema(description = "执行状态对应字典值")
    private String executeStatusDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "executeStatusDictId",mapValueField = "name")
    @Schema(description = "执行状态对应字典名称")
    private String executeStatusDictName;

    @Schema(description = "执行错误时提示信息")
    private String errorMessage;

    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;

    @Schema(description = "本地主机ip")
    private String localHostIp;

    @Schema(description = "本地主机名称")
    private String localHostName;

    @Schema(description = "链路追踪id")
    private String traceId;

    @Schema(description = "运行结果")
    private String result;

    @Schema(description = "数据过期时间")
    private LocalDateTime dataExpireAt;



}
