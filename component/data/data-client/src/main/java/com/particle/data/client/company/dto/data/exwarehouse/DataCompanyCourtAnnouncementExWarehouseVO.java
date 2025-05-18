package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业法院公告 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "公告号")
    private String announcementNo;

    @Schema(description = "公告类型")
    private String announcementType;

    @Schema(description = "案号")
    private String caseNo;

    @Schema(description = "案由")
    private String caseReason;

    @Schema(description = "法院公司id")
    private Long courtCompanyId;

    @Schema(description = "法院名称")
    private String courtName;

    @Schema(description = "刊登板面页码")
    private String publishPage;

    @Schema(description = "刊登板面日期")
    private LocalDate publishPageDate;

    @Schema(description = "发布日期")
    private LocalDate publishDate;

	@Schema(description = "数据md5,announcement_no + announcement_type + case_no + case_reason + publish_date")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

    @Schema(description = "当事人信息")
    private List<DataCompanyCourtAnnouncementPartyExWarehouseVO> parties;

    @Schema(description = "法院公告文本内容")
    private DataCompanyCourtAnnouncementContentExWarehouseVO content;

}
