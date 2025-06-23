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
 * 企业主要人员职位 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyPrimeStaffPositionExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业主要人员表ID")
    private Long companyPrimeStaffId;

    @Schema(description = "职位名称")
    private String positionName;

    @Schema(description = "职位")
    private Long positionDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "positionDictId",mapValueField = "value")
    @Schema(description = "职位对应字典值")
    private String positionDictValue;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "positionDictId",mapValueField = "name")
    @Schema(description = "职位对应字典名称")
    private String positionDictName;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;



}
