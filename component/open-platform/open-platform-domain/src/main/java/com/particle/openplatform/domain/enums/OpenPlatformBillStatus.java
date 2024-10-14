package com.particle.openplatform.domain.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 账单状态 字典项
 * </p>
 *
 * @author yw
 * @since 2024-10-12 13:55:44
 */
public enum OpenPlatformBillStatus implements IDictItem {

    /**
     * 初始生成
     */
    init
    ,
    /**
     * 已发给客户
     */
    send_to_customer
    ,
    /**
     * 客户已确认
     */
    customer_confirmed
    ,
    /**
     * 账单已结清
     */
    bill_settled
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.open_platform_bill_status.groupCode();
    }

    /**
     * 账单状态 字典组
     */
    public enum Group implements IDictGroup {
        open_platform_bill_status;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

