package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标许可人 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Data
@Schema
public class DataCompanyIprTrademarkLicensePersonVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权商标许可表id")
    private Long companyIprTrademarkLicenseId;
    
    @Schema(description = "原始许可人名称")
    private String licenseName;
    
    @Schema(description = "中文许可人名称")
    private String licenseNameCn;
    
    @Schema(description = "英文许可人名称")
    private String licenseNameEn;
    
    @Schema(description = "是否为被许可人")
    private Boolean isLicensed;
    
    @Schema(description = "数据md5")
    private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
