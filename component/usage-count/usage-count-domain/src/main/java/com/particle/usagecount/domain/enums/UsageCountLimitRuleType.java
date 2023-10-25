package com.particle.usagecount.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 限制规则类型 字典项
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:37:09
 */
public enum UsageCountLimitRuleType implements IDictItem {

    /**
     * 按用户计数
     */
    user_count
    ,
    /**
     * 按租户计数
     */
    tenant_count
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.usage_count_limit_rule_type.groupCode();
    }

    /**
     * 限制规则类型 字典组
     */
    public enum Group implements IDictGroup {
        usage_count_limit_rule_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

