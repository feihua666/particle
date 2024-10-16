package com.particle.global.openapi.enums;

/**
 * <p>
 * 限制规则对象
 * </p>
 *
 * @author yangwei
 * @since 2024-10-14 13:21:32
 */
public enum LimitRuleTarget {

    /**
     * 仅限制client_id
     */
    client_id
    ,
    /**
     * 限制client_id和api，粒度更细
     */
    client_id_and_openapi
}
