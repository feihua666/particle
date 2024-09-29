package com.particle.openplatform.client.bill.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 开放平台应用开放接口月汇总 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Data
@Schema
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO extends AbstractBaseIdVO {

    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;
    
    @Schema(description = "应用id")
    private String appId;
    
    @Schema(description = "开放平台接口id")
    private Long openplatformOpenapiId;
    
    @Schema(description = "年")
    private Integer year;
    
    @Schema(description = "月")
    private Integer month;
    
    @Schema(description = "客户id")
    private Long customerId;
    
    @Schema(description = "调用总量")
    private Integer totalCall;
    
    @Schema(description = "调用计费总量")
    private Integer totalFeeCall;
    
    @Schema(description = "平均单价金额")
    private Integer averageUnitPriceAmount;
    
    @Schema(description = "总消费金额")
    private Integer totalFeeAmount;
    
    @Schema(description = "描述")
    private String remark;
    


}
