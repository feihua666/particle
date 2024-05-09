package com.particle.area.domain.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 区域类型 字典项
 * </p>
 *
 * @author yw
 * @since 2024-02-27 11:08:48
 */
public enum AreaType implements IDictItem {

    /**
     * 省
     */
    province
    ,
    /**
     * 市
     */
    city
    ,
    /**
     * 区/县
     */
    county
    ,
    /**
     * 乡镇、街道
     */
    town
    ,
    /**
     * 村
     */
    village
    ,
    /**
     * 国家
     */
    country
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.area_type.groupCode();
    }

    /**
     * 区域类型 字典组
     */
    public enum Group implements IDictGroup {
        area_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

