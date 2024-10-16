package com.particle.openplatform.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 限制规则方式 字典项
 * </p>
 *
 * @author yw
 * @since 2024-10-16 14:23:28
 */
public enum OpenPlatformLimitRuleType implements IDictItem {

    /**
     * 不限制
     */
    no_limit
    ,
    /**
     * 按调用次数限制
     */
    count_limit
    ,
    /**
     * 按调用计费次数限制
     */
    count_fee_limit
    ,
    /**
     * 按费用限制
     */
    fee_limit
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.open_platform_limit_rule_type.groupCode();
    }

    /**
     * 限制规则方式 字典组
     */
    public enum Group implements IDictGroup {
        open_platform_limit_rule_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

