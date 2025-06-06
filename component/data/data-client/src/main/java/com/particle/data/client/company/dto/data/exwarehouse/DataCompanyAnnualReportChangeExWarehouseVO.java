package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业年报变更 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyAnnualReportChangeExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;

    @Schema(description = "年报年度")
    private Integer year;

    @Schema(description = "序号")
    private Integer serialNumber;

    @Schema(description = "变更事项")
    private Long changeItemDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "changeItemDictId",mapValueField = "value")
    @Schema(description = "变更事项对应字典值")
    private String changeItemDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "changeItemDictId",mapValueField = "name")
    @Schema(description = "变更事项对应字典名称")
    private String changeItemDictName;

    @Schema(description = "变更事项")
    private String changeItemName;

    @Schema(description = "变更前内容")
    private String contentBefore;

    @Schema(description = "变更后内容")
    private String contentAfter;

    @Schema(description = "变更日期")
    private LocalDate changeDate;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
