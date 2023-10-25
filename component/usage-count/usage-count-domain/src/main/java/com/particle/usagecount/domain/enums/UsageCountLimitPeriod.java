package com.particle.usagecount.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 限制周期 字典项
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:37:35
 */
public enum UsageCountLimitPeriod implements IDictItem {

    /**
     * 按天限制
     */
    day_period
    ,
    /**
     * 按周限制
     */
    week_period
    ,
    /**
     * 按月限制
     */
    month_period
    ,
    /**
     * 按季度限制
     */
    quarter_period
    ,
    /**
     * 按年限制
     */
    year_period
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.usage_count_limit_period.groupCode();
    }

    /**
     * 限制周期 字典组
     */
    public enum Group implements IDictGroup {
        usage_count_limit_period;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}
