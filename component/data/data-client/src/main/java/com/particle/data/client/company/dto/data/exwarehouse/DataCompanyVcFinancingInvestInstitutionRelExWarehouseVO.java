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
 * 企业融资历史投资机构关系 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业融资表ID")
    private Long companyVcFinancingId;
    
    @Schema(description = "企业投资机构表id")
    private Long companyVcInvestInstitutionId;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}