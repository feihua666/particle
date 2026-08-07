package com.particle.crawler.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 爬虫触发方式 字典项
 * </p>
 *
 * @author yw
 * @since 2026-05-13 15:18:34
 */
public enum CrawlerTriggerType implements IDictItem {

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
        return Group.crawler_trigger_type.groupCode();
    }

    /**
     * 爬虫触发方式 字典组
     */
    public enum Group implements IDictGroup {
        crawler_trigger_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}
