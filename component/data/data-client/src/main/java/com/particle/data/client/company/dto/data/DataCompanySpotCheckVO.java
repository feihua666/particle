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
 * 企业抽查检查 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Data
@Schema
public class DataCompanySpotCheckVO extends AbstractBaseIdVO {

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
