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
 * 企业年报行政许可 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;
    
    @Schema(description = "年报年度")
    private Integer year;
    
    @Schema(description = "序号")
    private Integer serialNumber;
    
    @Schema(description = "许可文件名称")
    private String fileName;
    
    @Schema(description = "许可文件到期日期")
    private LocalDate validToDate;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
