package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业知识产权专利缴费信息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Data
@Schema
public class DataCompanyIprPatentPaymentVO extends AbstractBaseIdVO {

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