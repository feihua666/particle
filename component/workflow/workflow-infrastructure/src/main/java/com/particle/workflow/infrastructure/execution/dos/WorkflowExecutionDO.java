package com.particle.workflow.infrastructure.execution.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 工作流执行实例表
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Accessors(chain = true)
@Data
@TableName("component_workflow_execution")
public class WorkflowExecutionDO extends BaseDO {

    /**
    * 工作流定义ID
    */
    private Long workflowDefinitionId;

    /**
    * 执行时使用的版本ID
    */
    private Long workflowDefinitionHistoryId;

    /**
    * 执行状态字典id：running/success/failed
    */
    private Long statusDictId;

    /**
    * 触发方式字典id：manual/api/schedule
    */
    private Long triggerTypeDictId;

    /**
    * 当前执行节点ID（用于断点续跑，对应graph里的id）
    */
    private String nodeId;

	/**
	 * 数据来源执行ID
	 */
	private Long copiedWorkflowExecutionId;

    /**
    * 全局上下文数据json
    */
    private String contextJson;

    /**
    * 运行开始时间
    */
    private LocalDateTime startAt;
    
    /**
    * 运行结束时间
    */
    private LocalDateTime finishAt;
    
    /**
    * 错误信息
    */
    private String errorMsg;


}