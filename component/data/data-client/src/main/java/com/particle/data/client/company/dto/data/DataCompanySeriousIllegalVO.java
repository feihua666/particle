package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDate;
/**
 * <p>
 * 企业严重违法 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Data
@Schema
public class DataCompanySeriousIllegalVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "类别")
    private String type;
    
    @Schema(description = "列入原因")
    private String putReason;
    
    @Schema(description = "列入日期")
    private LocalDate putDate;
        
    @Schema(description = "作出列入决定机关公司id")
    private Long putInstituteCompanyId;
    
    @Schema(description = "作出列入决定机关名称")
    private String putInstituteName;
    
    @Schema(description = "移除原因")
    private String removeReason;
    
    @Schema(description = "移除日期")
    private LocalDate removeDate;
        
    @Schema(description = "作出移除决定机关公司id")
    private Long removeInstituteCompanyId;
    
    @Schema(description = "作出移除决定机关名称")
    private String removeInstituteName;

	@Schema(description = "数据md5,type + put_reason + put_date")
	private String dataMd5;

	@Schema(description = "最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动")
	private LocalDateTime latestHandleAt;
    


}