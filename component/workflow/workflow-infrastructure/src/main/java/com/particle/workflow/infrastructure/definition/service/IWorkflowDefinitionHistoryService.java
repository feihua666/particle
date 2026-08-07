package com.particle.workflow.infrastructure.definition.service;

import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionHistoryDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 工作流定义历史 服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:56:12
 */
public interface IWorkflowDefinitionHistoryService extends IBaseService<WorkflowDefinitionHistoryDO> {

    /**
     * 根据工作流定义id查询
     * @param workflowDefinitionId
     * @return
     */
    default List<WorkflowDefinitionHistoryDO> getByWorkflowDefinitionId(Long workflowDefinitionId) {
        Assert.notNull(workflowDefinitionId,"workflowDefinitionId 不能为空");
        return list(Wrappers.<WorkflowDefinitionHistoryDO>lambdaQuery().eq(WorkflowDefinitionHistoryDO::getWorkflowDefinitionId, workflowDefinitionId));
    }



    /**
     * 根据工作流定义id查询多个
     * @param workflowDefinitionIds
     * @return
     */
    default List<WorkflowDefinitionHistoryDO> getByWorkflowDefinitionIds(List<Long> workflowDefinitionIds) {
        Assert.notEmpty(workflowDefinitionIds,"workflowDefinitionIds 不能为空");
        return list(Wrappers.<WorkflowDefinitionHistoryDO>lambdaQuery().in(WorkflowDefinitionHistoryDO::getWorkflowDefinitionId, workflowDefinitionIds));
    }
            












}
