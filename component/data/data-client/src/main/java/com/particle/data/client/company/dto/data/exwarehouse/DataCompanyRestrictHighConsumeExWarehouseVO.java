package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业限制高消费 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyRestrictHighConsumeExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "案号")
    private String caseNo;

    @Schema(description = "文件链接")
    private String attachUrl;

    @Schema(description = "文件快照链接")
    private String attachSnapshotUrl;

    @Schema(description = "立案日期")
    private LocalDate fileCaseDate;

    @Schema(description = "发布日期")
    private LocalDate publishDate;

    @Schema(description = "执行法院公司id")
    private Long executeCourtCompanyId;

    @Schema(description = "执行法院名称")
    private String executeCourtName;

	@Schema(description = "数据md5,case_no + file_case_date + publish_date + execute_court_name")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;


    @Schema(description = "当事人信息")
    private List<DataCompanyRestrictHighConsumePartyExWarehouseVO> parties;

}
