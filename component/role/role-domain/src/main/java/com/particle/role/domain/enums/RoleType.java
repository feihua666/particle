package com.particle.role.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 角色类型 字典项
 * </p>
 *
 * @author yw
 * @since 2025-08-31 08:18:15
 */
public enum RoleType implements IDictItem {

    /**
     * 其它分类
     */
    other
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.role_type.groupCode();
    }

    /**
     * 角色类型 字典组
     */
    public enum Group implements IDictGroup {
        role_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}
