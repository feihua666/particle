package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 专利类型 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-16 17:44:57
 */
public enum PatentType implements IDictItem {

    /**
     * 发明
     */
    patent_invent
    ,
    /**
     * 发明授权
     */
    patent_authorization
    ,
    /**
     * 实用新型
     */
    utility_model
    ,
    /**
     * 外观设计
     */
    design_patent
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.patent_type.groupCode();
    }

    /**
     * 专利类型 字典组
     */
    public enum Group implements IDictGroup {
        patent_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

