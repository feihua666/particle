package com.particle.scheduler.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 任务计划执行记录状态 字典项
 * </p>
 *
 * @author yw
 * @since 2024-09-03 15:47:33
 */
public enum SchedulerRecordExecuteStatus implements IDictItem {

    /**
     * 执行成功
     */
    success
    ,
    /**
     * 执行失败
     */
    fail
    ,
    /**
     * 执行中
     */
    running
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.scheduler_record_execute_status.groupCode();
    }

    /**
     * 任务计划执行记录状态 字典组
     */
    public enum Group implements IDictGroup {
        scheduler_record_execute_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}
