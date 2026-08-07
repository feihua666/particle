package com.particle.workflow.infrastructure.definition.service;

import com.particle.workflow.infrastructure.definition.dos.WorkflowDefinitionDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 工作流定义 服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:48
 */
public interface IWorkflowDefinitionService extends IBaseService<WorkflowDefinitionDO> {



    /**
     * 根据项目id查询
     * @param workflowProjectId
     * @return
     */
    default List<WorkflowDefinitionDO> getByWorkflowProjectId(Long workflowProjectId) {
        Assert.notNull(workflowProjectId,"workflowProjectId 不能为空");
        return list(Wrappers.<WorkflowDefinitionDO>lambdaQuery().eq(WorkflowDefinitionDO::getWorkflowProjectId, workflowProjectId));
    }



    /**
     * 根据项目id查询多个
     * @param workflowProjectIds
     * @return
     */
    default List<WorkflowDefinitionDO> getByWorkflowProjectIds(List<Long> workflowProjectIds) {
        Assert.notEmpty(workflowProjectIds,"workflowProjectIds 不能为空");
        return list(Wrappers.<WorkflowDefinitionDO>lambdaQuery().in(WorkflowDefinitionDO::getWorkflowProjectId, workflowProjectIds));
    }
            











}
