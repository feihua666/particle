package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业融资历史投资机构关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Data
@Schema
public class DataCompanyVcFinancingInvestInstitutionRelVO extends AbstractBaseIdVO {

    @Schema(description = "企业融资表ID")
    private Long companyVcFinancingId;
    
    @Schema(description = "企业投资机构表id")
    private Long companyVcInvestInstitutionId;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
