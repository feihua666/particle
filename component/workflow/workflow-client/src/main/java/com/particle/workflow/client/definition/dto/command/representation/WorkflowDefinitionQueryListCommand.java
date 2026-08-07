package com.particle.workflow.client.definition.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 工作流定义 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Data
@Schema
public class WorkflowDefinitionQueryListCommand extends AbstractBaseQueryCommand {



    @Like
    @Schema(description = "工作流名称，左前缀匹配")
    private String name;



    @Schema(description = "项目id")
    private Long workflowProjectId;


    @Schema(description = "最新发布版本流程定义id")
    private Long latestPublishWorkflowDefinitionHistoryId;


    @Schema(description = "草稿版本流程定义id")
    private Long draftWorkflowDefinitionHistoryId;










}
