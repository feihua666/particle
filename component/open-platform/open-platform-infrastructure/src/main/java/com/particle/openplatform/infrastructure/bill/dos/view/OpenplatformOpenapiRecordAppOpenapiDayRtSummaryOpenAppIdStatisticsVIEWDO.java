package com.particle.openplatform.infrastructure.bill.dos.view;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * 开放平台应用开放接口日汇总统计视图对象
 * 主要用于是汇总
 * </p>
 *
 * @author yangwei
 * @since 2024-10-09 15:16:52
 */
@Data
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryOpenAppIdStatisticsVIEWDO extends DTO {

    /**
     * 开放平台应用id
     */
    private Long openplatformAppId;

    /**
     * 调用总量
     */
    private Integer totalCall;

    /**
     * 调用计费总量
     */
    private Integer totalFeeCall;

    /**
     * 平均单价金额，单位分
     */
    private BigDecimal averageUnitPriceAmount;

    /**
     * 总消费金额，单位分
     */
    private Integer totalFeeAmount;


}
