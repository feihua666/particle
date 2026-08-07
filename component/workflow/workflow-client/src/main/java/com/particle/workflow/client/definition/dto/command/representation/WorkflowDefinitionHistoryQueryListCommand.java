package com.particle.workflow.client.definition.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 工作流定义历史 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Data
@Schema
public class WorkflowDefinitionHistoryQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "工作流定义id")
    private Long workflowDefinitionId;


    @Schema(description = "定义版本号")
    private Integer workflowDefinitionVersion;


    @Schema(description = "流程图数据")
    private String graphDataJson;


    @Schema(description = "工作流级配置json")
    private String configJson;


    @Schema(description = "是否发布")
    private Boolean isPublish;









}
