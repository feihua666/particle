package com.particle.workflow.client.execution.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 工作流执行实例 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Data
@Schema
public class WorkflowExecutionCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "工作流定义ID 不能为空")
        @Schema(description = "工作流定义ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long workflowDefinitionId;


    @NotNull(message = "执行时使用的版本ID 不能为空")
        @Schema(description = "执行时使用的版本ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long workflowDefinitionHistoryId;


    @NotNull(message = "执行状态字典id：running/success/failed 不能为空")
        @Schema(description = "执行状态字典id：running/success/failed",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;


    @NotNull(message = "触发方式字典id：manual/api/schedule 不能为空")
        @Schema(description = "触发方式字典id：manual/api/schedule",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long triggerTypeDictId;


    @Schema(description = "当前执行节点ID（用于断点续跑")
    private String nodeId;

	@Schema(description = "数据来源执行ID")
	private Long copiedWorkflowExecutionId;


    @Schema(description = "全局上下文数据json")
    private String contextJson;


    @NotNull(message = "运行开始时间 不能为空")
        @Schema(description = "运行开始时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startAt;
    

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
    

    @Schema(description = "错误信息")
    private String errorMsg;









}