package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 专利当前权利状态 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-16 17:46:02
 */
public enum PatentCurrentRightStatus implements IDictItem {

    /**
     * 有权
     */
    has_right
    ,
    /**
     * 无权
     */
    no_right
    ,
    /**
     * 在审
     */
    under_review
    ,
    /**
     * 其它
     */
    other
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.patent_current_right_status.groupCode();
    }

    /**
     * 专利当前权利状态 字典组
     */
    public enum Group implements IDictGroup {
        patent_current_right_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

