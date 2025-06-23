package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.global.light.share.trans.anno.TransField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 企业主要人员 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyPrimeStaffExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;

    @Schema(description = "姓名")
    private String staffName;

    @Schema(description = "个人表ID")
    private Long staffCompanyPersonId;

    @Schema(description = "职位名称")
    private String positionNames;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

    @TransField
    @Schema(description = "职位信息")
    private List<DataCompanyPrimeStaffPositionExWarehouseVO> positions;

}
