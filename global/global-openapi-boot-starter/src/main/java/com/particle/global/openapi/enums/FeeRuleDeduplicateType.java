package com.particle.global.openapi.enums;

/**
 * <p>
 * 去重方式
 * </p>
 *
 * @author yangwei
 * @since 2024/9/27 11:44
 */
public enum FeeRuleDeduplicateType {

    /**
     * 不去重
     */
    no_deduplicate
    ,
    /**
     * 按天去重
     */
    each_day_deduplicate,
    /**
     * 按周去重
     */
    each_week_deduplicate
    ,
    /**
     * 按月去重
     */
    each_month_deduplicate
}
