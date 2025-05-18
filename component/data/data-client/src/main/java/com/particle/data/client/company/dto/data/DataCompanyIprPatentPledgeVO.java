package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利质押信息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Data
@Schema
public class DataCompanyIprPatentPledgeVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;
    
    @Schema(description = "质押登记号")
    private String pledgeNo;
    
    @Schema(description = "质押保全类型")
    private String pledgePreserveType;
    
    @Schema(description = "质押保全权力类型")
    private String pledgePreserveRightType;
    
    @Schema(description = "生效日期")
    private LocalDate effectiveDate;
        
    @Schema(description = "变更日期")
    private LocalDate changeDate;
        
    @Schema(description = "出质人")
    private String pledgor;
    
    @Schema(description = "质权人")
    private String pledgee;
    
    @Schema(description = "解除日期")
    private LocalDate rescissionDate;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
