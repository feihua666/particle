package com.particle.cms.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 内容审核状态 字典项
 * </p>
 *
 * @author yw
 * @since 2026-01-14 10:33:51
 */
public enum CmsContentAuditStatus implements IDictItem {

    /**
     * 待审核
     */
    wait_audit
    ,
    /**
     * 审核通过
     */
    pass_audit
    ,
    /**
     * 审核未通过
     */
    unpass_audit
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.cms_content_audit_status.groupCode();
    }

    /**
     * 内容审核状态 字典组
     */
    public enum Group implements IDictGroup {
        cms_content_audit_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

