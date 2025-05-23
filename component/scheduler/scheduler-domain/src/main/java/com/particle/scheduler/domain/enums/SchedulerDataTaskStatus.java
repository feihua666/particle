package com.particle.scheduler.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 数据任务运行状态 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-22 18:53:16
 */
public enum SchedulerDataTaskStatus implements IDictItem {

    /**
     * 已提交待处理
     */
    submited
    ,
    /**
     * 处理中
     */
    processing
    ,
    /**
     * 处理完成
     */
    complete
    ,
    /**
     * 处理错误
     */
    error
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.scheduler_data_task_status.groupCode();
    }

    /**
     * 数据任务运行状态 字典组
     */
    public enum Group implements IDictGroup {
        scheduler_data_task_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

