package com.particle.crm.client.tag.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户标签关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Data
@Schema
public class CrmCustomerTagRelVO extends AbstractBaseIdVO {

    @Schema(description = "客户id")
    private Long crmCustomerId;


    @TransBy(tableName = TransTableNameConstants.component_crm_customer, byFieldName = "crmCustomerId", mapValueField = "name")
    @Schema(description = "客户名称")
    private String crmCustomerName;

    @Schema(description = "标签id")
    private Long crmCustomerTagId;


    @TransBy(tableName = TransTableNameConstants.component_crm_customer_tag, byFieldName = "crmCustomerTagId", mapValueField = "name")
    @Schema(description = "标签名称")
    private String crmCustomerTagName;


}
