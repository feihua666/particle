package com.particle.workflow.client.execution.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 工作流执行实例 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Data
@Schema
public class WorkflowExecutionPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "工作流定义ID")
    private Long workflowDefinitionId;


    @Schema(description = "执行时使用的版本ID")
    private Long workflowDefinitionHistoryId;


    @Schema(description = "执行状态字典id：running/success/failed")
    private Long statusDictId;


    @Schema(description = "触发方式字典id：manual/api/schedule")
    private Long triggerTypeDictId;


    @Schema(description = "当前执行节点ID（用于断点续跑")
    private String nodeId;

	@Schema(description = "数据来源执行ID")
	private Long copiedWorkflowExecutionId;



    @Schema(description = "运行开始时间")
    private LocalDateTime startAt;
    

    @Schema(description = "运行结束时间")
    private LocalDateTime finishAt;
    









}