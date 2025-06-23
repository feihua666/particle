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
 * 企业行政许可 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyAdministrativeLicenseExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "许可编号 ")
    private String licenceNo;
    
    @Schema(description = "许可证书名称")
    private String licenceName;
    
    @Schema(description = "行政许可决定文书号")
    private String decisionDocumentNo;
    
    @Schema(description = "许可证决定文书名称")
    private String decisionDocumentName;
    
    @Schema(description = "许可决定日期")
    private LocalDate decisionDate;
        
    @Schema(description = "许可开始日期")
    private LocalDate fromDate;
        
    @Schema(description = "许可截止日期")
    private LocalDate endDate;
        
    @Schema(description = "许可内容")
    private String licenceContent;
    
    @Schema(description = "许可机关")
    private String institute;
    
    @Schema(description = "许可机关统一社会信用代码 ")
    private String instituteUscc;
    
    @Schema(description = "许可机关公司id")
    private Long instituteCompanyId;
    
    @Schema(description = "数据来源单位 ")
    private String dataFrom;
    
    @Schema(description = "数据来源单位统一社会信用代码")
    private String dataFromUscc;
    
    @Schema(description = "数据来源单位公司id")
    private Long dataFromCompanyId;
    
    @Schema(description = "许可类型")
    private String licenceType;
    
    @Schema(description = "许可备注")
    private String licenceRemark;
    
    @Schema(description = "是否数据标识为工商公示")
    private Boolean isDataFlagGs;
    
    @Schema(description = "是否数据标识为信用中国")
    private Boolean isDataFlagXyzg;
    
    @Schema(description = "数据md5")
    private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}