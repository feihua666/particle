package com.particle.workflow.infrastructure.execution.service;

import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 工作流执行实例 服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:57:51
 */
public interface IWorkflowExecutionService extends IBaseService<WorkflowExecutionDO> {

    /**
     * 根据工作流定义ID查询
     * @param workflowDefinitionId
     * @return
     */
    default List<WorkflowExecutionDO> getByWorkflowDefinitionId(Long workflowDefinitionId) {
        Assert.notNull(workflowDefinitionId,"workflowDefinitionId 不能为空");
        return list(Wrappers.<WorkflowExecutionDO>lambdaQuery().eq(WorkflowExecutionDO::getWorkflowDefinitionId, workflowDefinitionId));
    }



    /**
     * 根据工作流定义ID查询多个
     * @param workflowDefinitionIds
     * @return
     */
    default List<WorkflowExecutionDO> getByWorkflowDefinitionIds(List<Long> workflowDefinitionIds) {
        Assert.notEmpty(workflowDefinitionIds,"workflowDefinitionIds 不能为空");
        return list(Wrappers.<WorkflowExecutionDO>lambdaQuery().in(WorkflowExecutionDO::getWorkflowDefinitionId, workflowDefinitionIds));
    }
            
















}
