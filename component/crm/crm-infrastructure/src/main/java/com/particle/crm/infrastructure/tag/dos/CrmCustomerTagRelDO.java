package com.particle.crm.infrastructure.tag.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 客户标签关系表
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Data
@TableName("component_crm_customer_tag_rel")
public class CrmCustomerTagRelDO extends BaseDO {

    /**
    * 客户id
    */
    private Long crmCustomerId;

    /**
    * 标签id
    */
    private Long crmCustomerTagId;


}
