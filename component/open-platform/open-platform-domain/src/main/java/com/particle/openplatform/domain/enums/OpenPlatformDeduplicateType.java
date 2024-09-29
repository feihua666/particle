package com.particle.openplatform.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 去重方式 字典项
 * </p>
 *
 * @author yw
 * @since 2024-09-28 12:42:53
 */
public enum OpenPlatformDeduplicateType implements IDictItem {

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
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.open_platform_deduplicate_type.groupCode();
    }

    /**
     * 去重方式 字典组
     */
    public enum Group implements IDictGroup {
        open_platform_deduplicate_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

