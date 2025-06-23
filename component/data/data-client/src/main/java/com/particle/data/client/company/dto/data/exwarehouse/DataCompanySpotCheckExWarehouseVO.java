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
 * 企业抽查检查 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanySpotCheckExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "检查实施机关")
    private String checkInstitute;
    
    @Schema(description = "检查实施机关公司id")
    private Long checkInstituteCompanyId;
    
    @Schema(description = "检查类型")
    private String checkTypeName;
    
    @Schema(description = "检查日期")
    private LocalDate checkDate;
        
    @Schema(description = "检查结果")
    private String checkResult;
    
    @Schema(description = "数据md5")
    private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
