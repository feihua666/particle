package com.particle.component.light.share.dict;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 专利法律状态 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-16 17:45:31
 */
public enum PatentLegalStatus implements IDictItem {

    /**
     * 公布
     */
    publication
    ,
    /**
     * 申请恢复
     */
    application_restoration
    ,
    /**
     * 实质审查
     */
    substantive_examination
    ,
    /**
     * 授权
     */
    authorization
    ,
    /**
     * 部分无效
     */
    partial_invalidation
    ,
    /**
     * 权利恢复
     */
    rights_restoration
    ,
    /**
     * 有效期续展
     */
    term_extension
    ,
    /**
     * 公布驳回
     */
    published_rejection
    ,
    /**
     * 公布撤回
     */
    published_withdrawal
    ,
    /**
     * 公布视为撤回
     */
    published_deemed_withdrawal
    ,
    /**
     * 全部无效
     */
    total_invalidation
    ,
    /**
     * 撤销
     */
    revocation
    ,
    /**
     * 权利终止
     */
    rights_termination
    ,
    /**
     * 期限届满专利权终止
     */
    patent_termination_due_to_expiry
    ,
    /**
     * 未缴年费专利权终止
     */
    patent_termination_due_to_unpaid_fees
    ,
    /**
     * 主动放弃
     */
    voluntary_abandonment
    ,
    /**
     * 视为放弃
     */
    deemed_abandonment
    ,
    /**
     * 避重放弃
     */
    avoidance_abandonment
    ,
    /**
     * PCT未进入指定国（指定期内）
     */
    pct_non_entry_during_designation_period
    ,
    /**
     * PCT进入指定国（指定期内）
     */
    pct_entry_during_designation_period
    ,
    /**
     * PCT未进入指定国（指定期满）
     */
    pct_non_entry_after_designation_period
    ,
    /**
     * PCT进入指定国（指定期满）
     */
    pct_entry_after_designation_period
    ,
    /**
     * 专利申请和转移
     */
    patent_apply_and_transfer
    ,
    /**
     * 专利更正
     */
    patent_update
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.patent_legal_status.groupCode();
    }

    /**
     * 专利法律状态 字典组
     */
    public enum Group implements IDictGroup {
        patent_legal_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

