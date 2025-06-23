package com.particle.data.client.company.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业主要人员 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Data
@Schema
public class DataCompanyPrimeStaffVO extends AbstractBaseIdVO {

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



}
