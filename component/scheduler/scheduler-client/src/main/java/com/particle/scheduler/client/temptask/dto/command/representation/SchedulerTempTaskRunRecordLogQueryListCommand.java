package com.particle.scheduler.client.temptask.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 任务计划临时任务运行记录日志 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordLogQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "临时任务运行记录id")
    private Long schedulerTempTaskRunRecordId;


    @Schema(description = "日志级别")
    private String level;










}
