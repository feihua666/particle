package com.particle.scheduler.client.temptask.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 任务计划临时任务 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-08-28 11:36:47
 */
@Data
@Schema
public class SchedulerTempTaskQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "临时任务编码")
    private String code;


    @Schema(description = "临时任务名称")
    private String name;









}
