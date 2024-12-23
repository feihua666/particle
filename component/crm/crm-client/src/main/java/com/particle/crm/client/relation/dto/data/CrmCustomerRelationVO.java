package com.particle.crm.client.relation.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户与客户关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Data
@Schema
public class CrmCustomerRelationVO extends AbstractBaseIdVO {

    @Schema(description = "客户id")
    private Long crmCustomerId;


    @TransBy(tableName = TransTableNameConstants.component_crm_customer, byFieldName = "crmCustomerId", mapValueField = "name")
    @Schema(description = "客户名称")
    private String crmCustomerName;

    @Schema(description = "另一个客户id")
    private Long anotherCrmCustomerId;


    @TransBy(tableName = TransTableNameConstants.component_crm_customer, byFieldName = "anotherCrmCustomerId", mapValueField = "name")
    @Schema(description = "另一个客户名称")
    private String anotherCrmCustomerName;

    @Schema(description = "关系id")
    private Long crmCustomerRelationDefineId;

    @TransBy(tableName = TransTableNameConstants.component_crm_customer_relation_define, byFieldName = "crmCustomerRelationDefineId", mapValueField = "name")
    @Schema(description = "关系名称")
    private String crmCustomerRelationDefineName;

    @Schema(description = "关系详情描述")
    private String relationDetail;



}
