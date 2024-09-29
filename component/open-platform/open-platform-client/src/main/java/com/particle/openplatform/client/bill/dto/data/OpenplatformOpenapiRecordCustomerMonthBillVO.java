package com.particle.openplatform.client.bill.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 开放平台客户月账单 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Data
@Schema
public class OpenplatformOpenapiRecordCustomerMonthBillVO extends AbstractBaseIdVO {

    @Schema(description = "客户id")
    private Long customerId;
    
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
