package com.particle.openplatform.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 计费原因 字典项
 * </p>
 *
 * @author yw
 * @since 2024-09-29 13:24:47
 */
public enum OpenFlatformFeeReason implements IDictItem {

    /**
     * 未配置计费规则
     */
    not_exist_fee_rule
    ,
    /**
     * 未返回有效数据
     */
    not_exist_effective_value
    ,
    /**
     * 响应超时
     */
    response_timeout
    ,
    /**
     * 按次单价不去重计费
     */
    per_price_no_deduplicate
    ,
    /**
     * 按次单价首次计费
     */
    per_price_first
    ,
    /**
     * 按次单价 运行计费（不计费）
     */
    per_price_deduplicate
    ,
    /**
     * 按次单价 周期计费
     */
    per_price_first_more
    ,
    /**
     * 包天、周、月、季、年首次计费
     */
    each_day__year_first
    ,
    /**
     * 包天、周、月、季、年去重计费（不计费）
     */
    each_day__year_deduplicate
    ,
    /**
     * 其它不支持情况默认计费
     */
    other_not_support
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.open_flatform_fee_reason.groupCode();
    }

    /**
     * 计费原因 字典组
     */
    public enum Group implements IDictGroup {
        open_flatform_fee_reason;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

