package com.particle.workflow.domain.execution;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 工作流节点执行实例 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Data
@Entity
public class WorkflowExecutionNode extends AggreateRoot {

    private WorkflowExecutionNodeId id;

    /**
    * 工作流执行ID
    */
    private Long workflowExecutionId;

    /**
    * 节点ID（对应graph里的id）
    */
    private String nodeId;

    /**
    * 状态：pending/running/success/failed
    */
    private Long statusDictId;

    /**
    * 节点输入
    */
    private String inputJson;

    /**
    * 节点输出
    */
    private String outputJson;

    /**
    * 错误信息
    */
    private String errorMsg;

    /**
    * 重试次数
    */
    private Integer retryCount;

    /**
    * 运行开始时间
    */
    private LocalDateTime startAt;
    
    /**
    * 运行结束时间
    */
    private LocalDateTime finishAt;
    


    /**
     * 创建工作流节点执行实例领域模型对象
     * @return 工作流节点执行实例领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static WorkflowExecutionNode create(){
        return DomainFactory.create(WorkflowExecutionNode.class);
    }
}
