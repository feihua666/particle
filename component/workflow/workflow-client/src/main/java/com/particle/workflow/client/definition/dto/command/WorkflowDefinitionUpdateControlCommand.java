package com.particle.workflow.client.definition.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import com.particle.global.light.share.mybatis.anno.SetNullWhenNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * <p>
 * 工作流定义 控制更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-05-03 20:01:52
 */
@Data
@Schema
public class WorkflowDefinitionUpdateControlCommand extends AbstractBaseUpdateCommand {


    @SetNullWhenNull
    @Schema(description = "最新发布版本流程定义id")
    private Long latestPublishWorkflowDefinitionHistoryId;

    @SetNullWhenNull
    @Schema(description = "草稿版本流程定义id")
    private Long draftWorkflowDefinitionHistoryId;

}
