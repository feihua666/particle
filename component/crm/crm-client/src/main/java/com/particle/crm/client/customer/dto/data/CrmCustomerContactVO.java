package com.particle.crm.client.customer.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户联系方式 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Data
@Schema
public class CrmCustomerContactVO extends AbstractBaseIdVO {

    @Schema(description = "客户id")
    private Long crmCustomerId;


    @TransBy(tableName = TransTableNameConstants.component_crm_customer, byFieldName = "crmCustomerId", mapValueField = "name")
    @Schema(description = "客户名称")
    private String crmCustomerName;

    @Schema(description = "联系方式类型")
    private Long contactTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "contactTypeDictId",mapValueField = "name")
    @Schema(description = "联系方式类型对应字典名称")
    private String contactTypeDictName;
        
    @Schema(description = "联系方式")
    private String contact;
    
    @Schema(description = "备注")
    private String remark;
    


}
