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
 * 企业知识产权商标转让人 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyIprTrademarkTransferPersonExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业知识产权商标转让表id")
    private Long companyIprTrademarkTransferId;
    
    @Schema(description = "原始转让人名称")
    private String transferName;
    
    @Schema(description = "中文转让人名称")
    private String transferNameCn;
    
    @Schema(description = "英文转让人名称")
    private String transferNameEn;
    
    @Schema(description = "是否被转让人")
    private Boolean isTransferred;
    
    @Schema(description = "数据md5")
    private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}