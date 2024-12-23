package com.particle.crm.infrastructure.customer.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 客户联系方式表
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Data
@TableName("component_crm_customer_contact")
public class CrmCustomerContactDO extends BaseDO {

    /**
    * 客户id
    */
    private Long crmCustomerId;

    /**
    * 联系方式类型,字典id
    */
    private Long contactTypeDictId;

    /**
    * 联系方式
    */
    private String contact;

    /**
    * 备注
    */
    private String remark;


}
