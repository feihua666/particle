package com.particle.openplatform.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 计费方式 字典项
 * 由于考虑到后期的升级，分表，账单计费是先统计的日账单，所以把之前的想法，包季、年的计费方式，暂时先不支持，只支持按次，按天，按月，自定义配置（预留暂时没想好）
 * </p>
 *
 * @author yw
 * @since 2024-09-28 12:42:23
 */
public enum OpenPlatformFeeType implements IDictItem {

    /**
     * 按次
     */
    each_interface_call
    ,
    /**
     * 包天
     */
    each_day,

    /**
     * 包周
     */
    each_week,
    /**
     * 包月
     */
    each_month
    ,
    /**
     * 包季
     */
    each_season
    ,
    /**
     * 包年
     */
    each_year
    ,
    /**
     * 自定义配置
     */
    custom_config
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.open_platform_fee_type.groupCode();
    }

    /**
     * 计费方式 字典组
     */
    public enum Group implements IDictGroup {
        open_platform_fee_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

