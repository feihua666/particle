package com.particle.workflow.domain.enums;
import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 触发方式 字典项
 * </p>
 * 需要和 {@link com.particle.global.workflow.enums.WorkflowTriggerType} 保持一致
 * @author yw
 * @since 2026-05-02 19:14:29
 */
public enum WorkflowExecutionTriggerType implements IDictItem {

    /**
     * 手动触发
     */
    MANUAL
    ,
    /**
     * 定时触发
     */
    SCHEDULED
    ,
    /**
     * API触发
     */
    API
    ,
    /**
     * 事件触发
     */
    EVENT
    ,
    /**
     * Webhook触发
     */
    WEBHOOK
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.workflow_execution_trigger_type.groupCode();
    }

    /**
     * 触发方式 字典组
     */
    public enum Group implements IDictGroup {
        workflow_execution_trigger_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}
