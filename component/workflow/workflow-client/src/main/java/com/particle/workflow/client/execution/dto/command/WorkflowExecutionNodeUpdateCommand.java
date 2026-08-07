package com.particle.workflow.client.execution.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 工作流节点执行实例 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Data
@Schema
public class WorkflowExecutionNodeUpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "工作流执行ID 不能为空")
        @Schema(description = "工作流执行ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long workflowExecutionId;


    @NotEmpty(message = "节点ID（对应graph里的id） 不能为空")
        @Schema(description = "节点ID（对应graph里的id）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nodeId;


    @NotNull(message = "状态：pending/running/success/failed 不能为空")
        @Schema(description = "状态：pending/running/success/failed",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;


    @Schema(description = "节点输入")
    private String inputJson;


    @Schema(description = "节点输出")
    private String outputJson;


    @Schema(description = "错误信息")
    private String errorMsg;


    @Schema(description = "重试次数")
    private Integer retryCount;


    @NotNull(message = "运行开始时间 不能为空")
        @Schema(description = "运行开始时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime startAt;
    

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
    








}
