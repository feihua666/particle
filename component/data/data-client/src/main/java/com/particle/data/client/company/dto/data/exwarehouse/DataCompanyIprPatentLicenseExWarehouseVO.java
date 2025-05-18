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
 * 企业知识产权专利许可信息 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentLicenseExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;
    
    @Schema(description = "专利权许可类型")
    private String licenseType;
    
    @Schema(description = "专利备案合同号编码")
    private String filingContractNo;
    
    @Schema(description = "合同备案日期")
    private LocalDate filingContractDate;
        
    @Schema(description = "让与人")
    private String assignor;
    
    @Schema(description = "合同变更日期")
    private LocalDate contractChangeDate;
        
    @Schema(description = "受让人")
    private String assignee;
    
    @Schema(description = "合同解除日期")
    private LocalDate contractRescissionDate;

	@Schema(description = "数据md5,license_type + filing_contract_no + filing_contract_date + assignor + assignee")
	private String dataMd5;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}