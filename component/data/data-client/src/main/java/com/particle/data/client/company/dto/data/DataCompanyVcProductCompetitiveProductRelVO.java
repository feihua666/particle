package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业融资产品竞品关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Data
@Schema
public class DataCompanyVcProductCompetitiveProductRelVO extends AbstractBaseIdVO {

    @Schema(description = "企业融资产品表ID")
    private Long companyVcProductId;
    
    @Schema(description = "企业竞品id")
    private Long companyVcCompetitiveProductId;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
