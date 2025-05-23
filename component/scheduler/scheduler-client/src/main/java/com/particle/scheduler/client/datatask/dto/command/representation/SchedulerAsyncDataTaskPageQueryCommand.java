package com.particle.scheduler.client.datatask.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 任务计划异步任务数据 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:05:42
 */
@Data
@Schema
public class SchedulerAsyncDataTaskPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "任务分组标识")
    private String groupIdentifier;


    @Schema(description = "唯一标识")
    private String uniqueIdentifier;



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



    @Schema(description = "数据过期时间")
    private LocalDateTime dataExpireAt;
    








}
