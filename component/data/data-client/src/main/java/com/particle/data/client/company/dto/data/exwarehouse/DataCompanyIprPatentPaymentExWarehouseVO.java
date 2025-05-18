package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业知识产权专利缴费信息 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentPaymentExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;
    
    @Schema(description = "费用金额(元)")
    private BigDecimal feeAmount;
        
    @Schema(description = "费用种类")
    private String feeType;
    
    @Schema(description = "收据号")
    private String receiptNo;
    
    @Schema(description = "缴费人信息")
    private String payer;
    
    @Schema(description = "处理状态")
    private String handleStatus;
    
    @Schema(description = "缴费日期")
    private LocalDate payDate;

	@Schema(description = "数据md5,fee_type + receipt_no + payer + handle_status + pay_date")
	private String dataMd5;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}