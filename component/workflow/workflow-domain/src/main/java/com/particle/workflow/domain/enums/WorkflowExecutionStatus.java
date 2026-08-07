package com.particle.workflow.domain.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 工作流执行状态 字典项
 * </p>
 * 需要和 {@link com.particle.global.workflow.enums.WorkflowExecutionStatus} 保持一致
 * @author yw
 * @since 2026-05-02 19:13:53
 */
public enum WorkflowExecutionStatus implements IDictItem {

    /**
     * 待执行
     */
    PENDING
    ,
    /**
     * 运行中
     */
    RUNNING
    ,
    /**
     * 已完成
     */
    COMPLETED
    ,
    /**
     * 执行失败
     */
    FAILED
    ,
    /**
     * 已暂停
     */
    PAUSED
    ,
    /**
     * 已停止
     */
    STOPPED
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.workflow_execution_status.groupCode();
    }

    /**
     * 工作流执行状态 字典组
     */
    public enum Group implements IDictGroup {
        workflow_execution_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

