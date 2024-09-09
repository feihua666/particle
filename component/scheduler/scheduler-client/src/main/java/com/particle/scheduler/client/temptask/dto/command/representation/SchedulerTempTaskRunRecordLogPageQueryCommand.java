package com.particle.scheduler.client.temptask.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 任务计划临时任务运行记录日志 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:37:25
 */
@Data
@Schema
public class SchedulerTempTaskRunRecordLogPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "临时任务运行记录id")
    private Long schedulerTempTaskRunRecordId;


    @Schema(description = "日志级别")
    private String level;










}
