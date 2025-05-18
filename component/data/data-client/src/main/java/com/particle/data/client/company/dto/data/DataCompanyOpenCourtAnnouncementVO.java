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
 * 企业开庭公告 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Data
@Schema
public class DataCompanyOpenCourtAnnouncementVO extends AbstractBaseIdVO {

    @Schema(description = "案号")
    private String caseNo;

    @Schema(description = "案由")
    private String caseReason;

    @Schema(description = "法院名称")
    private String courtName;

    @Schema(description = "法庭")
    private String courtRoom;

    @Schema(description = "承办部门")
    private String undertakeDept;

    @Schema(description = "开庭日期")
    private LocalDate openDate;

    @Schema(description = "排期日期")
    private LocalDate planDate;

    @Schema(description = "审判长/主审人")
    private String presidingJudge;

    @Schema(description = "观看链接/视频链接")
    private String videoUrl;

	@Schema(description = "数据md5,case_no + case_reason + open_date")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
