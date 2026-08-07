package com.particle.workflow.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 工作流定义历史草稿 创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 20:47:01
 */
@Data
@Schema
public class WorkflowDefinitionHistoryCreateDraftCommand extends AbstractBaseCommand {

    @NotNull(message = "工作流定义id 不能为空")
        @Schema(description = "工作流定义id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long workflowDefinitionId;

    @Schema(description = "流程图数据")
    private String graphDataJson;


    @Schema(description = "工作流级配置json")
    private String configJson;


    public static WorkflowDefinitionHistoryCreateDraftCommand create(Long workflowDefinitionId,
                                                                     String graphDataJson,
                                                                     String configJson) {

        WorkflowDefinitionHistoryCreateDraftCommand workflowDefinitionHistoryCreateCommand = new WorkflowDefinitionHistoryCreateDraftCommand();
        workflowDefinitionHistoryCreateCommand.workflowDefinitionId = workflowDefinitionId;

        workflowDefinitionHistoryCreateCommand.graphDataJson = graphDataJson;
        workflowDefinitionHistoryCreateCommand.configJson = configJson;
        return workflowDefinitionHistoryCreateCommand;
    }

}
