package com.particle.openplatform.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 批量查询执行状态 字典项
 * </p>
 *
 * @author yw
 * @since 2024-09-19 15:32:07
 */
public enum OpenPlatformBatchQueryExecuteStatus implements IDictItem {

    /**
     * 未开始
     */
    not_start
    ,
    /**
     * 执行中
     */
    running
    ,
    /**
     * 执行完成
     */
    finished
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.open_platform_batch_query_execute_status.groupCode();
    }

    /**
     * 批量查询执行状态 字典组
     */
    public enum Group implements IDictGroup {
        open_platform_batch_query_execute_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

