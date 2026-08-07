package com.particle.workflow.domain.execution;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 工作流执行实例 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
@Data
@Entity
public class WorkflowExecution extends AggreateRoot {

    private WorkflowExecutionId id;

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



    /**
     * 创建工作流执行实例领域模型对象
     * @return 工作流执行实例领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static WorkflowExecution create(){
        return DomainFactory.create(WorkflowExecution.class);
    }
}