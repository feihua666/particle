package com.particle.openplatform.client.bill.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台应用月账单 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppMonthBillVO extends AbstractBaseIdVO {

    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_app, byFieldName = "openplatformAppId", mapValueField = "name")
    @Schema(description = "开放平台应用名称")
    private String openplatformAppName;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "客户id")
    private Long customerId;

    @TransBy(type = TransConstants.TRANS_CRM_CUSTOMER_BY_ID,byFieldName = "customerId",mapValueField = "name")
    @Schema(description = "客户id")
    private String customerName;

    @Schema(description = "年")
    private Integer year;
    
    @Schema(description = "月")
    private Integer month;
    
    @Schema(description = "调用总量")
    private Integer totalCall;
    
    @Schema(description = "调用计费总量")
    private Integer totalFeeCall;
    
    @Schema(description = "总消费金额")
    private Integer totalFeeAmount;
    
    @Schema(description = "账单状态")
    private Long statusDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "statusDictId",mapValueField = "name")
    @Schema(description = "账单状态对应字典名称")
    private String statusDictName;
        
    @Schema(description = "描述")
    private String remark;
    


}