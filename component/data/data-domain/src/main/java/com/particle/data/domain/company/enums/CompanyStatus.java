package com.particle.data.domain.company.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 企业登记状态 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-20 14:54:22
 */
public enum CompanyStatus implements IDictItem {

    /**
     * 存续/在业
     */
    operating
    ,
    /**
     * 迁入
     */
    moved_in
    ,
    /**
     * 迁出
     */
    moved_out
    ,
    /**
     * 注销
     */
    cancelled
    ,
    /**
     * 吊销
     */
    revoked
    ,
    /**
     * 撤销
     */
    reversed
    ,
    /**
     * 清算
     */
    liquidation
    ,
    /**
     * 停业
     */
    suspended
    ,
    /**
     * 歇业
     */
    ceased
    ,
    /**
     * 除名
     */
    stricken_off
    ,
    /**
     * 责令关闭
     */
    ordered_to_close
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.company_status.groupCode();
    }

    /**
     * 企业登记状态 字典组
     */
    public enum Group implements IDictGroup {
        company_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

