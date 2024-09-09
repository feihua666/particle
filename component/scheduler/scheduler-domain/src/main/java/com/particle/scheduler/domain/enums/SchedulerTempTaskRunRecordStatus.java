package com.particle.scheduler.domain.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 临时任务运行状态 字典项
 * </p>
 *
 * @author yw
 * @since 2024-08-28 16:43:04
 */
public enum SchedulerTempTaskRunRecordStatus implements IDictItem {

    /**
     * 未开始
     */
    not_start
    ,
    /**
     * 运行中
     */
    running
    ,
    /**
     * 运行完成
     */
    finished
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.scheduler_temp_task_run_record_status.groupCode();
    }

    /**
     * 临时任务运行状态 字典组
     */
    public enum Group implements IDictGroup {
        scheduler_temp_task_run_record_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

