package com.particle.workflow.infrastructure.definition.service;

import com.particle.workflow.infrastructure.definition.dos.WorkflowProjectDO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 工作流项目 服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-28 09:55:12
 */
public interface IWorkflowProjectService extends IBaseService<WorkflowProjectDO> {




    /**
     * 根据归属用户id查询
     * @param userId
     * @return
     */
    default List<WorkflowProjectDO> getByUserId(Long userId) {
        Assert.notNull(userId,"userId 不能为空");
        return list(Wrappers.<WorkflowProjectDO>lambdaQuery().eq(WorkflowProjectDO::getUserId, userId));
    }



    /**
     * 根据归属用户id查询多个
     * @param userIds
     * @return
     */
    default List<WorkflowProjectDO> getByUserIds(List<Long> userIds) {
        Assert.notEmpty(userIds,"userIds 不能为空");
        return list(Wrappers.<WorkflowProjectDO>lambdaQuery().in(WorkflowProjectDO::getUserId, userIds));
    }
            










}
