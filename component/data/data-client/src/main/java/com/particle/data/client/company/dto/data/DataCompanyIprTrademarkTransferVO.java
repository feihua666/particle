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
 * 企业知识产权商标转让信息 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Data
@Schema
public class DataCompanyIprTrademarkTransferVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权商标表id")
    private Long companyIprTrademarkId;
    
    @Schema(description = "转让期号")
    private String transferIssueNo;
    
    @Schema(description = "转让页码")
    private String transferPageNo;
    
    @Schema(description = "转让公告日期")
    private LocalDate transferPublicDate;
        
    @Schema(description = "数据md5")
    private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}
