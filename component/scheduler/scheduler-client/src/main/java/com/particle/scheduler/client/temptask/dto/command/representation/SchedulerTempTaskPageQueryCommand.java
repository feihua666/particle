package com.particle.scheduler.client.temptask.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 任务计划临时任务 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Data
@Schema
public class SchedulerTempTaskPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "临时任务编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "临时任务名称,左前缀匹配")
    private String name;









}
