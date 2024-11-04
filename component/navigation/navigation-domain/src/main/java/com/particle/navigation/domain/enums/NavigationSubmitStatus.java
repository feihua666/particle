package com.particle.navigation.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 提交状态 字典项
 * </p>
 *
 * @author yw
 * @since 2024-11-03 15:36:43
 */
public enum NavigationSubmitStatus implements IDictItem {

    /**
     * 新提交
     */
    new_submit
    ,
    /**
     * 数据补充中
     */
    data_appending
    ,
    /**
     * 数据补充完成
     */
    data_appended
    ,
    /**
     * 已提交
     */
    submitted
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.navigation_submit_status.groupCode();
    }

    /**
     * 提交状态 字典组
     */
    public enum Group implements IDictGroup {
        navigation_submit_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

