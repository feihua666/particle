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
 * 企业知识产权专利统计 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprPatentStatisticExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;
    
    @Schema(description = "同族专利数量")
    private Integer familyNum;
    
    @Schema(description = "扩展同族专利数量")
    private Integer extFamilyNum;
    
    @Schema(description = "被引证数量")
    private Integer citedNum;
    
    @Schema(description = "引证专利数量")
    private Integer quoteNum;
    
    @Schema(description = "权利要求数量")
    private Integer claimNum;
    
    @Schema(description = "独权数")
    private Integer independentClaimNum;
    
    @Schema(description = "从权数")
    private Integer dependentClaimNum;
    
    @Schema(description = "转让次数")
    private Integer transferNum;
    
    @Schema(description = "许可次数")
    private Integer licenseNum;
    
    @Schema(description = "质押次数")
    private Integer pledgeNum;
    
    @Schema(description = "无效次数")
    private Integer invalidNum;
    
    @Schema(description = "诉讼次数")
    private Integer litigationNum;
    
    @Schema(description = "IPC分类数量")
    private Integer ipcCategoryNum;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}