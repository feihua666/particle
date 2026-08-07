package com.particle.workflow.domain.definition;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 工作流定义 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
@Data
@Entity
public class WorkflowDefinition extends AggreateRoot {

    private WorkflowDefinitionId id;

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

    public void changeDraftWorkflowDefinitionHistoryId(Long draftWorkflowDefinitionHistoryId) {
        this.draftWorkflowDefinitionHistoryId = draftWorkflowDefinitionHistoryId;
    }

    public void changeLatestPublishWorkflowDefinitionHistoryId(Long latestPublishWorkflowDefinitionHistoryId) {
        this.latestPublishWorkflowDefinitionHistoryId = latestPublishWorkflowDefinitionHistoryId;
    }


    /**
     * 创建工作流定义领域模型对象
     * @return 工作流定义领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static WorkflowDefinition create(){
        return DomainFactory.create(WorkflowDefinition.class);
    }
}
