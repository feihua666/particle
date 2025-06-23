package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业送达公告 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "案号")
    private String caseNo;

    @Schema(description = "案由")
    private String caseReason;

    @Schema(description = "公告标题")
    private String title;

    @Schema(description = "法院名称")
    private String courtName;

    @Schema(description = "发布日期")
    private LocalDate publishDate;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

    @Schema(description = "当事人信息")
    private List<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> parties;

    @Schema(description = "开庭送达文本内容")
    private DataCompanyDeliveryAnnouncementContentExWarehouseVO content;
}
