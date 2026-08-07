package com.particle.workflow.infrastructure.definition.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 工作流定义历史表
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Accessors(chain = true)
@Data
@TableName("component_workflow_definition_history")
public class WorkflowDefinitionHistoryDO extends BaseDO {

    /**
    * 工作流定义id
    */
    private Long workflowDefinitionId;

    /**
    * 定义版本号，从1开始递增
    */
    private Integer workflowDefinitionVersion;

    /**
    * 流程图数据
    */
    private String graphDataJson;

    /**
    * 工作流级配置json
    */
    private String configJson;

    /**
    * 是否发布，1=已发布，0=未发布，草稿
    */
    private Boolean isPublish;


}
