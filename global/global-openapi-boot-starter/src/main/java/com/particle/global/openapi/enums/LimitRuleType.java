package com.particle.global.openapi.enums;

/**
 * <p>
 * 限制规则类型或方式
 * </p>
 *
 * @author yangwei
 * @since 2024-10-14 13:21:32
 */
public enum LimitRuleType {


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
}
