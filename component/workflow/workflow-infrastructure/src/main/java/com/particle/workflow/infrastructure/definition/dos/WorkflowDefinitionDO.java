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
 * 工作流定义表
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Accessors(chain = true)
@Data
@TableName("component_workflow_definition")
public class WorkflowDefinitionDO extends BaseDO {

    /**
    * 工作流名称
    */
    private String name;

    /**
    * 封面图地址
    */
    private String coverImageUrl;

    /**
    * 项目id
    */
    private Long workflowProjectId;

    /**
    * 最新发布版本流程定义id
    */
    private Long latestPublishWorkflowDefinitionHistoryId;

    /**
    * 草稿版本流程定义id
    */
    private Long draftWorkflowDefinitionHistoryId;

    /**
    * 描述
    */
    private String remark;


}
