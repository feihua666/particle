package com.particle.global.openapi.enums;

/**
 * <p>
 * 计费类型
 * </p>
 *
 * @author yangwei
 * @since 2024/9/27 11:44
 */
public enum FeeRuleFeeType {

    /**
     * 按次，每次接口调用需要计算计费
     */
    each_interface_call
    ,
    /**
     * 包周，每个自然周计算一次计费
     */
    each_week
    ,
    /**
     * 包月，每个自然月计算一次计费
     */
    each_month
    ,
    /**
     * 包季，每个自然季度计算一次计费
     */
    each_season
    ,
    /**
     * 包年，每个自然年计算一次计费
     */
    each_year
    ,
    /**
     * 自定义配置，按自定义频率计算一次计费
     */
    custom_config
}
