package com.particle.crm.infrastructure.tag.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 客户标签表
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Data
@TableName("component_crm_customer_tag")
public class CrmCustomerTagDO extends BaseDO {

    /**
    * 标签编码
    */
    private String code;

    /**
    * 标签名称
    */
    private String name;

    /**
    * 备注
    */
    private String remark;


}
