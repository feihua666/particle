package com.particle.scheduler.client.temptask.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划临时任务运行记录 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:05
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "临时任务id")
    private Long schedulerTempTaskId;


    @Schema(description = "临时任务状态")
    private Long executeStatusDictId;


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