package com.particle.audit.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 审核结果 字典项
 * </p>
 *
 * @author yw
 * @since 2026-01-19 16:50:34
 */
public enum AuditResult implements IDictItem {

    /**
     * 同意
     */
    pass
    ,
    /**
     * 不同意
     */
    unpass
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.audit_result.groupCode();
    }

    /**
     * 审核结果 字典组
     */
    public enum Group implements IDictGroup {
        audit_result;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

