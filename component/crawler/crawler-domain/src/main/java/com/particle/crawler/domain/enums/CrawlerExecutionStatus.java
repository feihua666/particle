package com.particle.crawler.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 爬虫执行状态 字典项
 * </p>
 *
 * @author yw
 * @since 2026-05-13 15:18:06
 */
public enum CrawlerExecutionStatus implements IDictItem {

    /**
     * 等待执行
     */
    PENDING
    ,
    /**
     * 正在执行
     */
    RUNNING
    ,
    /**
     * 执行成功
     */
    SUCCESS
    ,
    /**
     * 执行失败
     */
    FAILED
    ,
    /**
     * 已取消
     */
    CANCELLED
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.crawler_execution_status.groupCode();
    }

    /**
     * 爬虫执行状态 字典组
     */
    public enum Group implements IDictGroup {
        crawler_execution_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}


