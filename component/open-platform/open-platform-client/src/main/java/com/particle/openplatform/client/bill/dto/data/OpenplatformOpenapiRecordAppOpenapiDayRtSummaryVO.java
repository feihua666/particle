package com.particle.openplatform.client.bill.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * <p>
 * 开放平台应用开放接口日实时汇总 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO extends AbstractBaseIdVO {

    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_app, byFieldName = "openplatformAppId", mapValueField = "name")
    @Schema(description = "开放平台应用名称")
    private String openplatformAppName;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "开放平台接口id")
    private Long openplatformOpenapiId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_openapi, byFieldName = "openplatformOpenapiId", mapValueField = "name")
    @Schema(description = "开放接口名称")
    private String openplatformOpenapiName;

    @Schema(description = "日期")
    private LocalDate dayAt;

    @Schema(description = "客户id")
    private Long customerId;

    @TransBy(type = TransConstants.TRANS_CRM_CUSTOMER_BY_ID,byFieldName = "customerId",mapValueField = "name")
    @Schema(description = "客户id")
    private String customerName;

    @Schema(description = "调用总量")
    private Integer totalCall;

    @Schema(description = "调用计费总量")
    private Integer totalFeeCall;

    @Schema(description = "平均单价金额")
    private BigDecimal averageUnitPriceAmount;

    @Schema(description = "总消费金额")
    private Integer totalFeeAmount;

    @Schema(description = "描述")
    private String remark;


}
