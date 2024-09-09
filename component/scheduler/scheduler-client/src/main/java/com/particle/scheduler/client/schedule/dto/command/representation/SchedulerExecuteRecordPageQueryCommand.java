package com.particle.scheduler.client.schedule.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划执行记录 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:25:23
 */
@Data
@Schema
public class SchedulerExecuteRecordPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "schedulerName")
    private String schedulerName;


    @Schema(description = "schedulerInstanceId")
    private String schedulerInstanceId;


    @Schema(description = "任务名称")
    private String name;


    @Schema(description = "任务组")
    private String groupName;



    @Schema(description = "执行状态")
    private Long executeStatusDictId;


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










}
