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
 * 企业知识产权商标质押信息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Data
@Schema
public class DataCompanyIprTrademarkPledgeVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权商标表id")
    private Long companyIprTrademarkId;
    
    @Schema(description = "出质人")
    private String pledgor;
    
    @Schema(description = "质权人")
    private String pledgee;
    
    @Schema(description = "质权登记起始日期")
    private LocalDate pledgeRegStartDate;
        
    @Schema(description = "质权登记截止日期")
    private LocalDate pledgeRegEndDate;
        
    @Schema(description = "质权公告页号")
    private String pledgePublicPageNo;
        
    @Schema(description = "数据md5")
    private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
