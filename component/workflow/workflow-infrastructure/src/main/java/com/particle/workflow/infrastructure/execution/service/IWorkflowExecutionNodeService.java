package com.particle.workflow.infrastructure.execution.service;

import com.particle.workflow.infrastructure.execution.dos.WorkflowExecutionNodeDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 工作流节点执行实例 服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:58:15
 */
public interface IWorkflowExecutionNodeService extends IBaseService<WorkflowExecutionNodeDO> {

    /**
     * 根据工作流执行ID查询
     * @param workflowExecutionId
     * @return
     */
    default List<WorkflowExecutionNodeDO> getByWorkflowExecutionId(Long workflowExecutionId) {
        Assert.notNull(workflowExecutionId,"workflowExecutionId 不能为空");
        return list(Wrappers.<WorkflowExecutionNodeDO>lambdaQuery().eq(WorkflowExecutionNodeDO::getWorkflowExecutionId, workflowExecutionId));
    }



    /**
     * 根据工作流执行ID查询多个
     * @param workflowExecutionIds
     * @return
     */
    default List<WorkflowExecutionNodeDO> getByWorkflowExecutionIds(List<Long> workflowExecutionIds) {
        Assert.notEmpty(workflowExecutionIds,"workflowExecutionIds 不能为空");
        return list(Wrappers.<WorkflowExecutionNodeDO>lambdaQuery().in(WorkflowExecutionNodeDO::getWorkflowExecutionId, workflowExecutionIds));
    }
            
















}
