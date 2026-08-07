package com.particle.workflow.domain.definition;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 工作流定义历史 领域模型
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
@Data
@Entity
public class WorkflowDefinitionHistory extends AggreateRoot {

    private WorkflowDefinitionHistoryId id;

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

    public void initForAdd() {
        changeWorkflowDefinitionVersion(1);
        changeUnPublish();
    }

    public void changeWorkflowDefinitionVersion(Integer workflowDefinitionVersion) {
        this.workflowDefinitionVersion = workflowDefinitionVersion;
    }
    public void changePublish() {
        changeIsPublish(true);
    }
    public void changeUnPublish() {
        changeIsPublish(false);
    }
    private void changeIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

    /**
     * 创建工作流定义历史领域模型对象
     * @return 工作流定义历史领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static WorkflowDefinitionHistory create(){
        return DomainFactory.create(WorkflowDefinitionHistory.class);
    }
}
