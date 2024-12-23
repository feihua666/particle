package com.particle.crm.infrastructure.relation.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 客户与客户关系表
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Data
@TableName("component_crm_customer_relation")
public class CrmCustomerRelationDO extends BaseDO {

    /**
    * 客户id
    */
    private Long crmCustomerId;

    /**
    * 另一个客户id
    */
    private Long anotherCrmCustomerId;

    /**
    * 关系id，描述为客户是另一个客户的关系，如：张三是李四的父亲
    */
    private Long crmCustomerRelationDefineId;

    /**
    * 关系详情描述
    */
    private String relationDetail;


}
