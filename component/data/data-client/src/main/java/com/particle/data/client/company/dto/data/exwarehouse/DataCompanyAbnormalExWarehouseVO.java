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
 * 企业经营异常 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyAbnormalExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "企业名称")
    private String companyName;

    @Schema(description = "列入决定书文号")
    private String putNo;

    @Schema(description = "列入异常名录原因")
    private String putReason;

    @Schema(description = "列入异常名录日期")
    private LocalDate putDate;

    @Schema(description = "作出列入决定机关公司id")
    private Long putInstituteCompanyId;

    @Schema(description = "作出列入决定机关名称")
    private String putInstituteName;

    @Schema(description = "移出决定书文号")
    private String removeNo;

    @Schema(description = "移出异常名录原因")
    private String removeReason;

    @Schema(description = "移出异常名录日期")
    private LocalDate removeDate;

    @Schema(description = "作出移出决定机关公司id")
    private Long removeInstituteCompanyId;

    @Schema(description = "作出移出决定机关名称")
    private String removeInstituteName;

    @Schema(description = "数据md5")
    private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
