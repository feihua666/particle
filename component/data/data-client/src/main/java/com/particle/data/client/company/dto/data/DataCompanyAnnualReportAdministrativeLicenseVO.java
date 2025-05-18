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
 * 企业年报行政许可 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Data
@Schema
public class DataCompanyAnnualReportAdministrativeLicenseVO extends AbstractBaseIdVO {

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

	@Schema(description = "数据md5,file_name")
	private String dataMd5;
        
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}