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
 * 工作流节点执行实例表
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
@Accessors(chain = true)
@Data
@TableName("component_workflow_execution_node")
public class WorkflowExecutionNodeDO extends BaseDO {

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
    

}
