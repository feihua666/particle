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
 * 企业送达公告内容 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementContentExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业送达公告id")
    private Long companyDeliveryAnnouncementId;
    
    @Schema(description = "公告内容")
    private String content;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}