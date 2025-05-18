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
 * 企业年报股权变更 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyAnnualReportEquityChangeExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;

    @Schema(description = "年报年度")
    private Integer year;

    @Schema(description = "序号")
    private Integer serialNumber;

    @Schema(description = "股东名称")
    private String shareholderName;

    @Schema(description = "是否股东为自然人")
    private Boolean isShareholderNaturalPerson;

    @Schema(description = "股东公司id")
    private Long shareholderCompanyId;

    @Schema(description = "股东个人id")
    private Long shareholderCompanyPersonId;

    @Schema(description = "变更前比例")
    private BigDecimal percentBefore;

    @Schema(description = "变更后比例")
    private BigDecimal percentAfter;

    @Schema(description = "变更日期")
    private LocalDate changeDate;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
