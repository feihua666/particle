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
 * 企业知识产权地理标识核准公告 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprGeograApproveAnnouncementExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权地理标识id")
    private Long companyIprGeograId;
    
    @Schema(description = "核准公告编号")
    private String approvePublicNo;
    
    @Schema(description = "企业名称")
    private String companyName;
    
    @Schema(description = "核准地址")
    private String approveAddress;
    
    @Schema(description = "核准法人代表")
    private String approveLegalPersonName;
    
    @Schema(description = "产品名称")
    private String productName;
    
    @Schema(description = "核准商标")
    private String approveTrademark;
    
    @Schema(description = "核准日期")
    private LocalDate approveDate;
        
    @Schema(description = "核准备注")
    private String approveRemark;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}